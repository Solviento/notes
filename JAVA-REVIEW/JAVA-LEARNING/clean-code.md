<h1>Clean Code</h1>

**Goals of Clean Code**

- Differentiating good code from bad code

- Easier to read

- Better organization

- Easier maintenance

- Continued accuracy

- Creating extensible code

- Establish new habits for better coding

**Commenting**

- Should illuminate and explain

- Should not detract, distract, misinform

- Often difficult to maintain properly

- Comments do not make up for bad code

Do you need a comment?

- Review coding choices
- Meaningful identifiers can replace unclear code and take away the need to comment

**Good Commenting** 

| Usage                   | For example..                                                |
| ----------------------- | ------------------------------------------------------------ |
| Legal Purposes          | Copyright or authorship comments                             |
| Warning of consequences | Not thread safe, takes too long, etc.                        |
| TODO comments           | Remind to do something that can't be handled at the moment   |
| JavaDocs                | Comments for public API                                      |
| Amplification           | Drawing attention to importance of something                 |
| Informative             | Explanation of a regular expression, or format string (hard to understand) |
| Explanation of Intent   | The intent behind a feature or an implementation             |
| Clarifying Foreign Code | Clarification of code you cannot alter to make more readable |

**Bad Commenting**

| Usage                  | For example..                                                |
| ---------------------- | ------------------------------------------------------------ |
| Mumbling               | Confusing, no code understanding                             |
| Redundant/Noise        | Restating obvious                                            |
| Misleading             | Misleading wording that affects meaning                      |
| Mandated               | Ordered by company/department/etc                            |
| Journal                | Logs of changes, old practice                                |
| Non-obvious Connection | Not relevant to the code it is next to                       |
| Position Markers       | Mark a particular spot in a file                             |
| Bylines                | Credit code to their authors, old practice                   |
| Old code               | Never necessary to comment out code, just use source control |
| TMI                    | Discussions or irrelevant descriptions                       |

Identifiers

| Good Identifier           | Bad Identifier      |
| ------------------------- | ------------------- |
| Intention-revealing       | Noise words         |
| Pronounceable             | Encoded Names       |
| Searchable                | Mental Mapping      |
| Nouns for Class Names     | Misleading          |
| Verbs for Method Names    | Prefixes            |
| Proper Domain Terminology | Puns                |
| Added Meaning Context     | Trying to be clever |

Indentation

- Not indented
  - Class declarations, imports, package declarations
- Method declarations
  - One level to right of class
- Method bodies
  - One level to right of method declaration
- Block implementations
  - One level to right of containing block

Class Organization

- Variables (Standard Convention in Java)
  - First
    - Public static constants
  - Then
    - Private static variables
  - Finally
    - Private instance variables
  - There is rarely a good reason to have a public variable
- Public functions should follow the same order as variables

Vertical Formatting

- Declare variables close to their usage
  - Local variables at the top of each function
  - Instance variables in an agreed-upon location
- Keep functions vertically close below where they are called

Data Hiding

- Keep variables and utility functions private
- Testability is important
  - Protected/package access might be needed for testing
- Always look for a way to maintain privacy
  - Loosening encapsulation is a last resort

Small Classes

- Each class should only have one responsibility
- Class name should describe all it does
  - Try describing the class with one sentence WITHOUT using if, and, or, but
- When a class loses cohesion, split it up
- Breaking a large function into smaller functions often gives an opportunity to split several smaller classes out as well































