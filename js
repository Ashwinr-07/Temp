import whisper
import os
import shutil

# Load the tiny model (this will also cache it internally the first time)
print("📦 Downloading and loading Whisper tiny model...")
model = whisper.load_model("tiny")

# Save model to the current repo as tiny.pt
target_path = os.path.join(os.getcwd(), "tiny.pt")
print(f"💾 Copying cached model to: {target_path}")

# Locate the cached model file from Whisper's cache directory
cache_dir = os.path.expanduser("~/.cache/whisper")
model_file = os.path.join(cache_dir, "tiny.pt")

# Copy the model file to the current directory
shutil.copyfile(model_file, target_path)

print("✅ Done! tiny.pt saved to current folder.")
