Git - Quick Sheet:
-------------------------

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

Normal Workflow:
	git add -A ..
	git commit -m "Fixed .."
	git push origin master

	If Merge Errors occur:
		git pull --rebase origin master
		Fix and resolve all conflicts indicated by git
		git add ..
		git rebase --continue
		git push origin master

Industrial Workflow:
	git checkout master
	git pull --rebase origin master
	git checkout -b fix-part-B
	
	Once ready to merge to master branch
		git add -A ..
		git commit -m "Fixed.."
		git push origin fix-part-B

		git pull --rebase origin master

Problems and solutions

"updates were rejected because the remote contains work that you do not have"
// likely due to adding a new README file

git remote add origin [//github url]

git pull origin master

git push origin master

"updates were rejected because the tip of your current branch is behind"
// likely due to adding mutliple commits from different machines

git push -f origin <branch>
