# The Hack Assembler

### Purpose
The Hack Assembler exists for the purpose of translating Hack Assembly Language into machine language which is then read by the Hack CPU to carry out instructions.
This is built to the specifications outlined in the book "The Elements of Computing Systems".

### Specification
This project consists of three modules which interact in order to translate assembly language.

- Parser: Encapsulates access to the input assembly code and provides a convenient means for advancing through the source code, skipping comments and white space, and breaking each symbolic instruction into its underlying components.
- Code: Provides services for translating symbolic Hack mnemonics into their binary codes according to the language specifications.
- Symbol Table: A Hash Table designed to create and maintain the correspondence between symbols and their meaning.

### Testing
Unit testing exists for every module and a test exists for every method to ensure expected behavior.