package ru.devray.school.regard.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;


public class ShoppingCartPage extends AbstractPage{

    private String shoppingCartContentsXpath = "//table[@id='table-basket']//tr[@class='goods_line']/td[3]/a";

    public ShoppingCartPage() {
        super("https://www.regard.ru/basket/");
    }

    public void checkShoppingCartContentsNotEmpty(){
        List<WebElement> shoppingCartContents = driver.findElements(By.xpath(shoppingCartContentsXpath));
        Assertions.assertTrue(shoppingCartContents.size() > 0, "Корзина пуста!");
    }

    public void verifyShoppingCartHasValidItems(List<String> expectedItems){
        List<String> shoppingCartContents = driver.findElements(By.xpath(shoppingCartContentsXpath))
                .stream().map(e -> e.getText()).collect(Collectors.toList());

        log.info("Ожидаемое наполнение корзины: " + expectedItems);
        log.info("Реальное наполнение корзины: " + shoppingCartContents);

        Assertions.assertEquals(expectedItems.size(), shoppingCartContents.size(),
                "Количество покупок в корзине не соответствует ожидаемому!");

        Assertions.assertTrue(shoppingCartContents.containsAll(expectedItems),
                "Покупки в корзине не содержат все ожидаемые товары!");
    }
}
