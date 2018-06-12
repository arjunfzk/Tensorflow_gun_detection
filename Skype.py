from selenium import webdriver
import time
#date
def main():
    driver = webdriver.Chrome()
    driver.get('https://www.skype.com/en/')
    time.sleep(4)
    driver.find_element_by_xpath("//*[@id='nav-buttons-wrapper']/nav/ul/li[4]/a[1]/span[1]").click()
    driver.find_element_by_xpath("//*[@id='nav-buttons-wrapper']/nav/ul/li[4]/ul/li[2]/a").click()
    time.sleep(5)
    driver.find_element_by_xpath("//*[@id='i0116']").send_keys("")
    driver.find_element_by_xpath("//*[@id='idSIButton9']").click()
    time.sleep(1)
    driver.find_element_by_xpath("//*[@id='i0118']").send_keys("")
    driver.find_element_by_xpath("//*[@id='idSIButton9']").click()

    time.sleep(16)
    driver.find_element_by_xpath("//div[@id='timelineComponent']/div/swx-sidebar/swx-recents/div[2]/div/div/swx-recent-item/a/div/span[3]/span/div/p").click()
    time.sleep(10)
    driver.find_element_by_xpath('//*[@id="swxContent1"]/swx-navigation/div/div/div/swx-header/div[1]/div/div/div/div/swx-button[1]/button').click()
    time.sleep(20)
    #driver.find_element_by_xpath("(//button[@type='button'])[20]").click()
    #time.sleep(20)

if __name__ == '__main__':
    main()
