import pyautogui as pg
import time, random

pg.press('win')
pg.typewrite('Notepad', 0.2)
pg.press('enter')
time.sleep(3)

array = ['Madarchod', 'Behenchod', 'Jhaat ke baal',
         'Bhadwe', 'Randi', 'Gaand phatike', 'Fuck You',
         'Behen ke lund', 'Gaandu', 'Chutiye', 'DickHead',
         'Randi ki Choot', 'Betichod', 'Chodu', 'Hijre',
         'Laude', 'Choot Marike', 'Chinaal']

counter = 0
index = 0
while True:
    if(counter == 4):
        pg.typewrite('\n')
        counter = 0
    index = random.randint(0, len(array) - 1) 
    pg.typewrite(array[index] + '   ', 0.05)
    time.sleep(0.5)
    counter += 1
