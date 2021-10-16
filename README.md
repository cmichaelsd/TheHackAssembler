# The Hack Assembler

### Purpose
The Hack Assembler exists for the purpose of translating Hack Assembly Language into machine language which is then read by the Hack CPU to carry out instructions.
This is built to the specifications outlined in the book "The Elements of Computing Systems".

### Specification
This project consists of three modules which interact in order to translate assembly language.

- Parser: Encapsulates access to the input assembly code and provides a convenient means for advancing through the source code, skipping comments and white space, and breaking each symbolic instruction into its underlying components.
- Code: Provides services for translating symbolic Hack mnemonics into their binary codes according to the language specifications.
- Symbol Table: A Hash Table designed to create and maintain the correspondence between symbols and their meaning.

### Parser
| Routine | Arguments | Returns | Function |
|---------|-----------|---------|----------|
| Constructor / initializer | Input file / stream | - | Opens the input file / stream and gets ready to parse it. |
| hasMoreLines | - | boolean | Determines if there are any more lines. |
| advance | - | - | Skips over white space and comments, if necessary. Reads the next instruction from the input, and makes it the current instruction. This routine should be called only if hasMoreLines is true. Initially there is no current instruction. |
| instructionType | - | A_INSTRUCTION, c_INSTRUCTION, l_INSTRUCTION (constants) | Returns the type of the current instruction: A_INSTRUCTION for @xxx, where xxx is either a decimal number or a symbol. C_INSTRUCTION for dest=comp;jump L_INSTRUCTION for (xxx), where xxx is a symbol. |
| symbol | - | string | If the current instruction is (xxx), returns the symbol xxx. If the current instruction is @xxx, returns the symbol or decimal xxx (as a string). Should be called only if instructionType is A_INSTRUCTION or L_INSTRUCTION. |
| dest | - | string | Returns the symbolic dest part of the current C-instruction (8 possibilities). Should be called only in instructionType is C_INSTRUCTION. |
| comp | - | string | Returns the symbolic comp part of the current C-instruction (28 possibilities). Should be called only in instructionType is C_INSTRUCTION. |
| jump | - | string | Returns the symbolic jump part of the current C-instruction (8 possibilities). Should be called only in instructionType is C_INSTRUCTION. |

### Code
| Routine | Arguments | Returns | Function |
|---------|-----------|---------|----------|
| dest | string | 3 bits, as a string | Returns the binary code of the dest mnemonic. |
| comp | string | 7 bits, as a string | Returns the binary code of the comp mnemonic. |
| jump | string | 3 bits, as a string | Returns the binary code of the jump mnemonic. |

### Symbol Table
| Routine | Arguments | Returns | Function |
|---------|-----------|---------|----------|
| Constructor / initializer | - | - | Creates a new empty symbol table. |
| addEntry | symbol (string), address (int) | - | Adds <symbol, address> to the table. |
| contains | symbol (string) | boolean | Determines if the table contains the given symbol |
| getAddress | symbol (string) | int | Returns the address associated with the symbol. |

### Testing
Unit testing exists for every module and a test exists for every method to ensure expected behavior.