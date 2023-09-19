from selenium import webdriver
import time
import urllib
import os
import random
# 存圖位置
local_path = '_YilanScallionPancake'
# 爬取頁面網址 
url = 'https://pic.sogou.com/pics?query=%E5%AE%9C%E8%98%AD%E8%94%A5%E9%A4%85&w=05009900'  
  
# 目標元素的xpath
#xpath = '//div[@id="imgid"]/ul/li/a/img'
xpath = '//div/div/ul/li/div/a/img'
# 啟動chrome瀏覽器
chromeDriver = r'D:\Programs\Python\Python37-32\Lib\site-packages\chromedriver.exe' # chromedriver檔案放的位置
driver = webdriver.Chrome(chromeDriver) 
  
# 最大化窗口，因為每一次爬取只能看到視窗内的圖片  
driver.maximize_window()  
  
# 紀錄下載過的圖片網址，避免重複下載  
img_url_dic = {}  
  
# 瀏覽器打開爬取頁面
driver.get(url)  
  
# 模擬滾動視窗瀏覽更多圖片
pos = 0  
m = 0 # 圖片編號 
for i in range(100):  
    pos += i*500 # 每次下滾500  
    js = "document.documentElement.scrollTop=%d" % pos  
    driver.execute_script(js)  
    
    timeRan = random.randint(10,55)
    time.sleep(timeRan)
    
    for element in driver.find_elements_by_xpath(xpath):
        try:
            img_url = element.get_attribute('src')
            
            # 保存圖片到指定路徑
            if img_url != None and not img_url in img_url_dic:
                img_url_dic[img_url] = ''  
                m += 1
                # print(img_url)
                #ext = img_url.split('/')[-1] 記住原本的檔名
                # print(ext)
                filename = str(m) + 'YilanScallionPancake' +'.jpg'
                #filename = str(m) + 'takoyaki' + '_' + ext +'.jpg' 要留原檔名的話用ext
                print(filename)
                
                # 保存圖片
                urllib.request.urlretrieve(img_url, os.path.join(local_path , filename))
                
        except OSError:
            print('發生OSError!')
            print(pos)
            break;
            
driver.close()