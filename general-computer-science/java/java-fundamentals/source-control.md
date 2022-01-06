**Introduction**

- Version control or source control refers to tracking and managing changes to information.

- In software development, this means changes to the code base.

- Key benefits:
  - Ease of collaboration with other developers on the same code
    Ability to work on or maintain multiple versions of code simultaneously

- Version control could be a manual process, but most of the time established software tools are used.

**Version Control Systems**

- Such tools allow developers to:
  - Keep work in a central location
  - Ensure they are working on the latest version at all times
  - Refer back to or revert to a previous version at any time
  - Resolve editing conflicts when collaborating

- There are two main types:
  - Centralized VCS 
  - Distributed VCS

**Centralized VCS**

- Centralized Version Control Systems use a client-server model:
  - A remote server stores the main (latest) version of the code, and its history
  - Clients connect to it, and check out  a working copy
  - Clients make changes to this local copy and connect again to commit  them as the latest version
    E.g. CVS (1990), SVN (2000)

**Distributed VCS**

- Distributed Version Control Systems use a peer-to-peer model:
  - Each client’s working copy is a complete repository, with its full history
  - Clients make changes to their local repos and exchange them
  - No remote communication unless sharing changes
  - E.g. Git (2005), Mercurial (2005)

**Git**

- At FDM, you will be using Git to manage your code:
  - Distributed VCS
  - Created by Linus Torvalds for work on the Linux kernel
  - Simple command-line tool
  - Can track changes to any file set
- You will be given an account on FDM’s GitLab server. 
- There, you can store your code in remote repositories and manage them from the command line.

**Git Commands – Setting Up**

- When setting up to use Git, you will do the following:
  - Create an empty repo (project) through GitLab’s web interface
  - Clone it to your local machine with git clone
  - Work on this local copy of the repo, saving changes to GitLab when necessary

- Note:  This lesson covers basic commands, but there are many more available. It is a good idea to research them and experiment with the features Git offers.

**Git Commands – Workflow**

- Once you have a local copy of the repo, you can do the following:

<img src="../PHOTOS/git-01.png">

**Best Practices – Commit Comments**

- When you commit, you will be asked to submit a comment detailing the new changes in this new version of the code.

- Which of the below comments do you think adds the most value?
  - “Made some changes to project”
  - “ ”
  - “Amended connection pool to allow for returning of connections”

**Best Practices – Files**

- Files to upload: 		Source code, occasionally required metadata
- Files to exclude: 	Compiled code (binaries, bytecode, executables, jars, etc.)
- Unsuitable: 			Slide decks, personal MP3s, videos, personal files

**Review Questions**

- What is the purpose of source control?
- What tools are available?
- What are some key best practices to use?
- Name several Git commands and describe their purpose.





















