/**
 * Created by svanlaar on 18/07/2016.
 */
var LOCATORS = {
    firstName  : '//*[@id="payment-details-first-name"]',
    priceHotel   : '//*[@id="financial-details-total-price"]',
    bookButton : ' //*[@id="book-button"]',
}

var printName = function(name) {
    print('Hi there from Javascript, ' + name);
    return "greetings from javascript";
};

var doSelenium = function () {
    // use some java driver stuff here and see if we can run it.
    this.driver.useXpath().click(LOCATORS['bookButton'])
}