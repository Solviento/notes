Git Review Sheet:
-------------------------

Usual Git Commands

``` git
git clone https://something.git

git pull origin release/2021.03.21
git reset --hard origin/release/2021.03.21

git add something.java
git commit -m "Added function"

git push origin master:feature/something123

// conflict with remote?
git pull origin release/2021.03.21
```

git add ...
	Tells git to add file(s) to commit stage

git commit -m 'message'
	Commits all files from stage, includes short message about commit

git init
	Initializes current directory as git enabled

git push
	More information needed how to utilize this correctly

git push origin master
	Used in conjuction with master-branch(es) 
	Pushes and merges all changes from working directory to git repository

git remote
	Connects to a remote server, more information needed

git clone 'url'
	Clones a git respository to current working directory. Can obtain a
	git clone url from most GitHub online repositories.

git checkout 'master/ or MyBranch/ or etc'
	Switches to either master branch or some working branch in the git 
	repository. MyBranch is an example of a branch that someone may work 
	on without any consequence to the master branch.

git pull
	Good for updating a local branch when a remote branch is updated with
	changes.

git pull --rebase origin master
	Attempts to integrate the new commits from GitHub with the new commits
	on your computer

	If Merge Errors occur:
		git pull --rebase origin master
		Fix and resolve all conflicts indicated by git
		git add ..
		git rebase --continue
		git push origin master

