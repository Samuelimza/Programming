@echo off
cd D:\NewFolder\Osama\Programming
git status
set /p add=Do you want to add(Y/N)?
IF %add%==Y (
	git add -A
	
) ELSE (
	echo Why you here man?, FUCKOFF!!
	PAUSE
	exit
)
set /p commit=Do you want to commit(Y/N)?
IF %commit%==Y (
	git commit -m "automatedCommit"
) ELSE (
	echo If you didn't want to commit then why Add?, FUCKOFF!!
	PAUSE
	exit
)
set /p push=Do you want to push(Y/N)?
IF %push%==Y (
	git push origin master
) ELSE (
	echo I understand you don't want to push yet, just FUCKOFF!!
	PAUSE
	exit
)
PAUSE