package ru.devray.school.regard;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.devray.school.regard.pages.MainPage;
import ru.devray.school.regard.pages.ProductCategory;
import ru.devray.school.regard.pages.ProductPage;
import ru.devray.school.regard.pages.ShoppingCartPage;
import ru.devray.school.regard.util.WebDriverWrapper;

import java.util.ArrayList;
import java.util.List;


public class SimpleTest {

    //Список, в котором будут храниться наименования покупок.
    List<String> shoppingList = new ArrayList<>();

    @Test
    @DisplayName("Тест пользовательской корзины покупок (физ. лица)")
    public void testRegardShoppingCartFeature(){

        MainPage mainPage = new MainPage();

        // 1. Открыть главную страницу
        mainPage.open();

        // 2. В левом боковом меню выбрать категорию материнские платы -
        // в подменю выбрать Intel socket 1200
        mainPage.openMenuWithSubCategory(ProductCategory.MOTHERBOARDS, "Intel Socket 1200");

        // 3. Найти 5-ю в списке материнскую плату, положить ее в корзину (маленькая круглая красная кнопочка около товара).
        mainPage.buyMarketItem(5, shoppingList);

        //4. В левом боковом меню выбрать категорию корпуса - в подменю выбрать Aerocool
        mainPage.openMenuWithSubCategory(ProductCategory.COMPUTER_CASES, "AEROCOOL");

        //5. Найти 4-й в списке корпус, положить ее в корзину (маленькая круглая красная кнопочка около товара).
        mainPage.buyMarketItem(4, shoppingList);

        //6. Найти 10-й в списке корпус, кликнуть на ссылку-название (откроется страница товара).
        mainPage.openMarketItem(10);

        //7. Нажать на красную кнопку "Добавить в корзину" справа от товара.
        ProductPage productPage = new ProductPage();
        productPage.addProductToShoppingCart(shoppingList);

        //8. Нажать на голубую кнопку "Перейти в корзину".
        productPage.clickGoToShoppingCartButton();

        //9. Убедиться, что корзина не пустая, и что в ней содержаться ровно те наименования товаров, которые вы покупали.
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage();
        shoppingCartPage.checkShoppingCartContentsNotEmpty();
        shoppingCartPage.verifyShoppingCartHasValidItems(shoppingList);
    }

}
