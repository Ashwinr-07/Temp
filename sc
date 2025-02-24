import re
from sumy.models.dom import Document, Sentence
from sumy.summarizers.lsa import LsaSummarizer
from sumy.utils import get_stop_words

def create_document_from_text(text):
    """
    Manually splits the text into sentences and builds a Sumy Document.
    This bypasses Sumy's default Tokenizer which requires nltk punkt.
    """
    # Split text on sentence-ending punctuation (., !, or ?)
    raw_sentences = re.split(r'[.!?]+\s*', text)
    # Remove any empty strings
    raw_sentences = [s.strip() for s in raw_sentences if s.strip()]
    # Create Sentence objects with an index
    sentences = [Sentence(sentence, idx) for idx, sentence in enumerate(raw_sentences)]
    return Document(sentences)

def main():
    transcript_file = "transcript.txt"  # Ensure your transcript file is here
    with open(transcript_file, "r", encoding="utf-8") as f:
        text = f.read()

    # Create a document manually from the transcript text
    document = create_document_from_text(text)

    # Initialize the LSA summarizer and set stop words
    summarizer = LsaSummarizer()
    summarizer.stop_words = get_stop_words("english")

    # Set the number of sentences you want in your summary
    SUMMARY_SENTENCES = 5
    summary = summarizer(document, SUMMARY_SENTENCES)

    # Print each sentence as a bullet point
    for sentence in summary:
        print("- " + str(sentence))

if __name__ == "__main__":
    main()
