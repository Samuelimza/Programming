cd D:\NewFolder\Osama\Programming
git status
set /p add=Do you want to add(Y/N)?
IF %add%==Y (
	git add -A
	
) ELSE (
	echo fuckoff
	exit
)
set /p commit=Do you want to commit(Y/N)?
IF %commit%==Y (
	git commit -m "automatedCommit"
)
PAUSE