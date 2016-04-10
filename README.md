# PredictiveKeyboard

A simple example implementation of how a predictive keyboard might work. This was written for an Asymmetrik interview
assignment. It works by populating a trie data structure with a training phrase and then querying it with partial
fragments, which would represent a word as a user is typing.

It might be interesting to add a buffer pool layer on top of this to cache frequently used queries.
## 

## Installation

Clone the repo and run `mvn package` to build with maven

## Usage

Run with:
`java -jar target/TextPredictor-1.0-SNAPSHOT-jar-with-dependncies.jar --training-string "TRAINING STRING" --fragment "fragment"`

Example Parameters:
`--training-string "The third thing that I need to tell you is that this thing does not think thoroughly." --fragment "th"`