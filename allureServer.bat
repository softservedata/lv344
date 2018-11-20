@echo off
set path=.\.allure\allure-2.0.1\bin\;%path%
rem .allure\allure-2.0.1\bin\allure serve target\allure-results

allure serve .\target\allure-results
rem allure serve allure-results

echo Press any key to continue ...
pause
