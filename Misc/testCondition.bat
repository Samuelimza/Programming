@echo off
cd D:\NewFolder\Osama\Programming
git status
set /p add=Do you want to add(Y/N)?
IF %add%==Y (
	git add -A
	
) ELSE (
	echo if dont wanna add then fuckoff
	PAUSE
	exit
)
set /p commit=Do you want to commit(Y/N)?
IF %commit%==Y (
	git commit -m "automatedCommit"
) ELSE (
	echo if dont wanna commit then fuckoff
	PAUSE
	exit
)
set /p push=Do you want to push(Y/N)?
IF %push%==Y (
	git push origin master
) ELSE (
	echo fuckoff
	PAUSE
	exit
)
PAUSE