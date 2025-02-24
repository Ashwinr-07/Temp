import re
from sumy.parsers.plaintext import PlaintextParser
from sumy.nlp.tokenizers import Tokenizer
from sumy.summarizers.lsa import LsaSummarizer
from sumy.utils import get_stop_words

class CustomTokenizer(Tokenizer):
    """
    A naive tokenizer that splits on sentence-final punctuation
    and splits words by whitespace, avoiding NLTK's punkt.
    """

    def to_sentences(self, text):
        # Split on '.', '?', or '!' followed by optional whitespace
        raw_sentences = re.split(r'[.?!]+\s*', text)
        raw_sentences = [s.strip() for s in raw_sentences if s.strip()]

        # Convert each sentence into a Sumy Sentence object
        from sumy.models.dom import Sentence
        sentences = [Sentence(s, i) for i, s in enumerate(raw_sentences)]
        return sentences

    def tokenize_words(self, sentence):
        # Naive whitespace-based word splitting
        return sentence.split()

def main():
    # Path to your transcript file
    transcript_file = "transcript.txt"

    # Read the transcript text
    with open(transcript_file, "r", encoding="utf-8") as f:
        text = f.read()

    # Create a parser using our CustomTokenizer
    parser = PlaintextParser.from_string(text, CustomTokenizer("english"))

    # Initialize the LSA Summarizer (you can use others: LexRankSummarizer, LuhnSummarizer, etc.)
    summarizer = LsaSummarizer()
    summarizer.stop_words = get_stop_words("english")  # Sumy's built-in stopwords

    # Number of sentences in the final summary
    SUMMARY_SENTENCES = 5

    # Summarize
    summary = summarizer(parser.document, SUMMARY_SENTENCES)

    # Print the summary as bullet points
    for sentence in summary:
        print("-", sentence)

if __name__ == "__main__":
    main()
