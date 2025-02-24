import re
from sumy.summarizers.lsa import LsaSummarizer
from sumy.utils import get_stop_words

# Define a minimal custom Sentence class
class CustomSentence:
    def __init__(self, text, index):
        self.text = text
        self.index = index

    def __str__(self):
        return self.text

    @property
    def words(self):
        # Split sentence into words (simple whitespace split)
        return self.text.split()

    @property
    def stem_words(self):
        # For simplicity, lowercase the words
        return [word.lower() for word in self.words]

# Define a minimal custom Document class that holds sentences
class CustomDocument:
    def __init__(self, sentences):
        self.sentences = sentences

def create_document_from_text(text):
    """
    Splits the text into sentences using regex and returns a CustomDocument.
    """
    # Split text on sentence-ending punctuation
    raw_sentences = re.split(r'[.!?]+\s*', text)
    # Remove empty strings and trim whitespace
    raw_sentences = [s.strip() for s in raw_sentences if s.strip()]
    # Create CustomSentence objects for each sentence
    sentences = [CustomSentence(sentence, idx) for idx, sentence in enumerate(raw_sentences)]
    return CustomDocument(sentences)

def main():
    transcript_file = "transcript.txt"  # Ensure this file exists in your directory
    with open(transcript_file, "r", encoding="utf-8") as f:
        text = f.read()

    # Create a document from the transcript text
    document = create_document_from_text(text)

    # Initialize the LSA summarizer and set stop words
    summarizer = LsaSummarizer()
    summarizer.stop_words = get_stop_words("english")

    # Set the number of sentences you want in your summary
    SUMMARY_SENTENCES = 5
    summary = summarizer(document, SUMMARY_SENTENCES)

    # Print each summarized sentence as a bullet point
    for sentence in summary:
        print("- " + str(sentence))

if __name__ == "__main__":
    main()
