| Unix nesQuik                                                 |                                                              |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
|                                                              |                                                              |
| Data Commands                                                |                                                              |
|                                                              |                                                              |
| cat [option] [file(s)]                                       | concat files and prints them                                 |
| cat appleRecipe                                              | normal file printout                                         |
|                                                              |                                                              |
| cmp [option] file1 file2                                     | compare files                                                |
| cmp apples oranges                                           | compare apples and oranges file                              |
|                                                              |                                                              |
| env                                                          | runs a prgm in a modded environment  by itself, will return $shell, $user, etc. |
|                                                              |                                                              |
| cut [option] [file]                                          | remove sections from EACH line of file  -c, selects only characters  -d, delimiter  -f, field selection  -s, ignore lines not containing delimiter |
| cut –c3 accounts                                             | gets 3rd char of each line                                   |
| cut –c4- accounts                                            | gets 4th to end of line                                      |
| cut –c4,9 accounts                                           | gets only 4th and 9th  char of each line                     |
| cut –c1-5 accounts                                           | gets first five chars of each line                           |
| cut –d”:” –f2,4 accounts                                     | delimit “:”, retrieve 2nd and 4th  field                     |
| cut –d”:” –f2-5 accounts                                     | delimit “:”, retrieve 2nd thru 5th  field                    |
| who \| cut –d” “ –f1                                         | delimit “ “, retrieve 1st field of  who                      |
|                                                              |                                                              |
| diff [option] files ...                                      | compare files line by line  -y, compare side by side         |
|                                                              |                                                              |
| echo [option] [file, variable, function]                     | display input as text  -n, don’t output trailing newline  -e, enable ‘\’ escapes like \n, \e, \b |
|                                                              |                                                              |
| grep [option] [pattern] [file(s), dir]                       | print lines matching a pattern  -E, extended regexp, “[a-zA-Z]”  -F, fixed string pattern, “fixedXYZ”  -c, counts number of lines with matches  -w, match WHOLE word that is matched  -i, ignore capitalization differences  -v, invert pattern matches  -n, prefix each output line with line num  -r, recursively read files in directory |
| grep “apple” grepFile                                        | finds lines containing letters: a,p,p,l,e                    |
| grep –w “orange” grepFile                                    | finds lines containing whole word “orange”                   |
| grep -E ‘^[0-9]’ grepFile                                    | finds lines starting with a digit                            |
|                                                              |                                                              |
| egrep                                                        | extended grep, same as grep -E                               |
| egrep ‘r+; grepFile                                          | finds lines with AT MOST 1 ‘r’                               |
| egrep ’65?’ grepFile                                         | finds lines with ‘6’ and AT LEAST 1 ‘5’                      |
| egrep ‘r(o\|e)’ grepFile                                     | finds lines with ‘r’ and EITHER ‘o’ or ‘e’                   |
|                                                              |                                                              |
| fgrep                                                        | LITERAL grep, same as grep -F                                |
| fgrep “guten tag” grepFile                                   | find lines with literal string “guten tag”                   |
| fgrep “water+” grepFile                                      | finds line with literal string “water+”                      |
|                                                              |                                                              |
| head [option] [file(s)]                                      | output first part of file(s)  -c, print first N bytes of each file  -n, print first N lines of each file |
| head -5 accounts                                             | retrieve first five lines                                    |
| head –c100 accounts                                          | retrieve first 100 characters                                |
|                                                              |                                                              |
| tail [option] [file(s)]                                      | output last part of files  -c, output last N bytes of each file  -n, output last N lines of each file |
|                                                              |                                                              |
| tr [option] [pattern1] [pattern2] [file]                     | translate/delete characters  -c, use COMPLEMENT of pattern1  -d, DELETE characters of pattern1 |
| tr “4” “*” < trFile                                          | replaces all “4”’s with “*”’s                                |
| tr [:lower:] [:upper:] < accounts                            | replace lowercase letters with uppercase                     |
| echo “chris” \| tr “crh” “Evl”                               | replace ‘c’ with ‘E’, ‘r’ with ‘v’, ‘h’ with  ‘l’            |
| echo “na12494d” \| tr –d [:digit:]                           | deletes all digits                                           |
| cat accounts \| tr –d “a-h”                                  | deletes all letters between a-h                              |
|                                                              |                                                              |
|                                                              |                                                              |
| less [file]                                                  | allows user to scroll read a file                            |
|                                                              |                                                              |
| more [file]                                                  | allows user to scroll down a file                            |
|                                                              |                                                              |
| sort [option] [file(s)]                                      | sort lines of text files  -b, ignore leading blanks, “  bye”  -n, numeric sort  -r, reverse sort order  -t, delimiter  -k, field selector |
| sort accounts                                                | sorts accounts alphanumerically                              |
| sort –r accounts                                             | sorts accounts in reverse                                    |
| sort –t”:” –k4 accounts                                      | delimit “:”, sort using 4th field                            |
| sort –nr –t”:” –k4 accounts                                  | delimit “:”, reverse sort numerically using 4th  field of each line |
|                                                              |                                                              |
| uniq [option] [input]                                        | report or omit repeated lines  -c, prefix results with number of oc  -d, print only duplicate lines  -i, ignore capitalization  -u, print only unique lines |
|                                                              |                                                              |
| wc [option] [file(s)]                                        | prnt nwline, word, byte counts for file(s)  -c, print byte counts  -m, print character counts  -l, print newline counts (# of lines)  -w, print word counts |
| wc –l accounts                                               | returns number of lines                                      |
| wc –w accounts                                               | returns number of words                                      |
| wc –c accounts                                               | returns number of bytes                                      |
| wc –m accounts                                               | returns number of characters                                 |
| cat accounts \| wc –l                                        | returns num of lines in the input stream                     |
| echo –n “Francie” \| wc –c                                   | returns num of chars minus the newline                       |
|                                                              |                                                              |
| File Commands                                                |                                                              |
|                                                              |                                                              |
| basename [file]                                              | strip directory and sffix from filenme                       |
|                                                              |                                                              |
| cd [dirname]                                                 | change directory                                             |
|                                                              |                                                              |
| chmod [option] [file(s)]                                     | change file permissions                                      |
| chmod 700 doMath                                             | enable read, write, execute permissions for  owner only      |
| chmod 070 doMath                                             | enable read, write, execute for group only                   |
| chmod 007 doMath                                             | enable read, write, execute for others only                  |
| chmod u+r doMath                                             | add read permission for owner                                |
| chmod g-w doMath                                             | remove write permission for group                            |
| chmod o=x doMath                                             | set only execute permission for others only                  |
|                                                              |                                                              |
| dirname [filepath]                                           | strip last component from filepath                           |
| dirname /usr/bin/                                            | returns “/usr”                                               |
|                                                              |                                                              |
| du [option] [file(s)]                                        | returns size of each file/directory  -a, return size for ALL files  -b, return size in bytes  -c, return grand total size  -h, return size in readable format  -S, do not count subdirectories  --time, show time of last modification |
| du –h fldrA                                                  | returns sizes of fldrA and subdir’s                          |
|                                                              |                                                              |
| cp [option] [source]  [destination]                          | copy files and/or directories  -i, prompt if file will be overwritten |
|                                                              |                                                              |
| file [file(s)]                                               | determine file type                                          |
|                                                              |                                                              |
| find [option] [file(s)]                                      | search for file in a directory  -empty, file is already empty  -size, state size of file  -user, state file owner  -delete, delete files  -exec command {}, execute command  -type, type of file to be found |
| find /var –name “*re*”  –size +100k –type f                  | find all FILES (not directories) that contain  “re” in directory “/var” with size 100kilobytes+ |
| find ~ -empty –ok rm                                         | find all empty files in home path dir                        |
| find . –atime -2                                             | find all files in current dir modified in the  past 2 days   |
| find /student_files –size  100c –exec wc {} \;               | find all files in “/student_files” with a size  less than 100 bytes THEN execute wc on those files |
|                                                              |                                                              |
| ln [option] [target] [destination]                           | create hard link between files,  hard links are references to a file’s PHYSICAL  memory location |
| ln bigApple.txt bigApple.txt.bak                             | creates backup hard link to txt file                         |
|                                                              |                                                              |
| ln -s                                                        | create soft link between files,  soft link is a reference pointer to some other  file or directory,  think Windows shortcuts on desktop |
| ln –s /too/many/dirs /fst                                    | create shortcut from “/too/many/dirs/” at “/fst”,  we can now do $cd /fst |
|                                                              |                                                              |
| ls [option]  [file(s)/directory]                             | list files in directory  -a, ALL files  -A, ALL except . and ..  -d, list directories not files  -f, do not sort  -h, human readable format  -l, LONG format  -s, sort by file size  -r, reverse sort order  -R, list subdirectories as well  -t, sort by modification times |
|                                                              |                                                              |
| mkdir [option]  [directory]                                  | create directory if it does not exist                        |
|                                                              |                                                              |
| mv [option] [source(s)]  [destination]                       | move/rename file(s)  -i, prompt if something overwrites  -n, DO NOT overwrite  -u, update if source file(s) is newer  -t, multiple file/dirs |
| mv back.jpg front.txt  ~/basket/                             | moves jpg and txt file into “/basket/”                       |
| mv bp bpm –t tmp  mv bp bpm –t tmp/                          | moves bp and bpm to tmp directory                            |
|                                                              |                                                              |
| pwd [option]                                                 | print name of current directory                              |
|                                                              |                                                              |
| rm [option] [file(s)]                                        | remove files or directories  -i, prompt before removal  -r, removes directories  -v, explain removal process |
| rm house.jpg boat.txt                                        | remove jpg and txt file in current dir                       |
| rm –r /bin/basket                                            | remove directory “/bin/basket”                               |
| rm –rf .                                                     | remove EVERYTHING in current directory                       |
|                                                              |                                                              |
| rmdir [option] [directory]                                   | remove empty directories                                     |
|                                                              |                                                              |
| stat [option] [file]                                         | display file status  -f, display file system status          |
|                                                              |                                                              |
| touch [option] [file(s)]                                     | change file timestamps or create new file(s)                 |
| touch newFile                                                | create or change timestamp for newFile                       |
|                                                              |                                                              |
| tree [directory]                                             | list directory content in tree format                        |
|                                                              |                                                              |
| Math and Logic Commands                                      |                                                              |
| ...                                                          | Not important, use your phone like a normal  person          |
|                                                              |                                                              |
| Process Commands                                             |                                                              |
|                                                              |                                                              |
| & [command]                                                  | run command in background                                    |
|                                                              |                                                              |
| bg %[jobID]                                                  | resume paused job, run in background  or convert foreground job to backgrnd |
| bg %1                                                        |                                                              |
|                                                              |                                                              |
| ctrl-c                                                       | terminate current job                                        |
|                                                              |                                                              |
| ctrl-z                                                       | pause current job                                            |
|                                                              |                                                              |
| fg %[jobID]                                                  | resume a paused job, run in foreground  or convert background job to foregrnd |
| fg %3                                                        |                                                              |
|                                                              |                                                              |
| jobs                                                         | list all jobs                                                |
|                                                              |                                                              |
| kill [processID]                                             | terminate a process                                          |
| kill 2                                                       | kills process with pid 2                                     |
|                                                              |                                                              |
| ps                                                           | list all running commands/programs                           |
|                                                              |                                                              |
|                                                              |                                                              |
| sleep [number]                                               | delay for specified time                                     |
| sleep 100                                                    | system sleep for 100 seconds                                 |
|                                                              |                                                              |
| System Commands                                              |                                                              |
|                                                              |                                                              |
| clear                                                        | clear terminal                                               |
|                                                              |                                                              |
| date                                                         | print date                                                   |
|                                                              |                                                              |
| env                                                          | print environment variables: $PATH, $USER,  $SHELL, etc.     |
|                                                              |                                                              |
| export                                                       | converts temp variable to permanent                          |
|                                                              |                                                              |
| finger                                                       | list all users on server                                     |
|                                                              |                                                              |
| sh                                                           | interprets commands                                          |
|                                                              |                                                              |
| users                                                        | print users as a long string                                 |
|                                                              |                                                              |
| w                                                            | show who’s logged on and what they’re doing                  |
|                                                              |                                                              |
| which [command]                                              | locate where a command is located                            |
| which touch                                                  | returns location of touch command                            |
|                                                              |                                                              |
| who                                                          | show who is logged on                                        |
|                                                              |                                                              |
| whoami                                                       | returns your username                                        |
|                                                              |                                                              |
|                                                              |                                                              |
| Regular Expressions                                          |                                                              |
| [0-9]                                                        |                                                              |
| [a-d]                                                        | matches any digit                                            |
| [:alpha:]                                                    | matches any letter btwn a and d                              |
| [:lower:]                                                    | matches any alphabet letter                                  |
| [:upper:]                                                    | matches lowercase letter                                     |
| [:digit:]                                                    | matches uppercase letter                                     |
| [:alnum:]                                                    | matches any digit                                            |
| ?                                                            | matches an alphanumeric character                            |
| *                                                            | preceding char is optional, ATMOST 1                         |
| +                                                            | preceding char is matched 0 OR MORE times                    |
| {n}                                                          | preceding char is matched 1 OR MORE times                    |
| {n,}                                                         | preceding char is matched EXACTLY n times                    |
| {n,m}                                                        | preceding char is matched n or MORE times                    |
|                                                              | preceding char is matched LEAST n times, AT  MOST m times    |
|                                                              |                                                              |
| SCRIPTING                                                    |                                                              |
|                                                              |                                                              |
| Special Variables                                            |                                                              |
| $0                                                           | script name                                                  |
| $#                                                           | number of command line parameters                            |
| $*                                                           | all command line parameters                                  |
| $@                                                           | all command line arguments                                   |
| $?                                                           | exit status of recent command                                |
| $$                                                           | process id of shell                                          |
| $!                                                           | process id of recent background command                      |
|                                                              |                                                              |
|                                                              |                                                              |
|                                                              |                                                              |
| Case                                                         |                                                              |
| function isChar(){    case  $1 in        [a-zA-Z])          return 0 ;;        *)          return 1 ;;    esac  } |                                                              |