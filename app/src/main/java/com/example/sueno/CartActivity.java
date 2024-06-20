package com.example.sueno;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CartActivity extends AppCompatActivity {

    private ListView ordersList;
    private TextView textViewEmptyCart1, textViewEmptyCart2, textViewClearCart, textViewCartStatus;
    private ArrayAdapter<String> adapter;
    private List<String> orderedItemsInfo;
    private ConstraintLayout balanceContainer, constraintLayout11, constraintLayout12, container;
    private ImageView imageView20, imageView21, imageView22, imageView32, imageView34;
    private TextView textView35, textView37, textView38, textView39, textView40, textView41, textView42, textView43, textViewB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        // Инициализация виджетов
        imageView20 = findViewById(R.id.imageView20);
        imageView21 = findViewById(R.id.imageView21);
        imageView22 = findViewById(R.id.imageView22);
        imageView32 = findViewById(R.id.imageView32);
        imageView34 = findViewById(R.id.imageView34);
        ordersList = findViewById(R.id.orders_list);
        textViewEmptyCart1 = findViewById(R.id.textViewEmptyCart1);
        textViewEmptyCart2 = findViewById(R.id.textViewEmptyCart2);
        textViewClearCart = findViewById(R.id.textView100);
        textViewCartStatus = findViewById(R.id.textView1);
        balanceContainer = findViewById(R.id.balanceContainer);
        constraintLayout11 = findViewById(R.id.constraintLayout11);
        constraintLayout12 = findViewById(R.id.constraintLayout12);
        container = findViewById(R.id.Container);
        textView35 = findViewById(R.id.textView35);
        textView37 = findViewById(R.id.textView37);
        textView38 = findViewById(R.id.textView38);
        textView39 = findViewById(R.id.textView39);
        textView40 = findViewById(R.id.textView40);
        textView41 = findViewById(R.id.textView41);
        textView42 = findViewById(R.id.textView42);
        textView43 = findViewById(R.id.textView43);
        textViewB = findViewById(R.id.textViewB);

        // Инициализация списка заказанных товаров и адаптера
        orderedItemsInfo = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, orderedItemsInfo);
        ordersList.setAdapter(adapter);

        setupConstraintLayoutListeners();

        // Установка обработчиков нажатий для кнопок навигации
        imageView20.setOnClickListener(v -> navigateToActivity(MainActivity.class));
        imageView21.setOnClickListener(v -> navigateToActivity(MenuActivity.class));
        imageView22.setOnClickListener(v -> navigateToActivity(StocksActivity.class));

        // Обработчик клика на элементе списка (для возможности редактирования товара)
        ordersList.setOnItemClickListener((parent, view, position, id) -> {
            Order.OrderItem selectedItem = Order.orderItems.get(position);
            Intent intent = new Intent(CartActivity.this, ChoiceActivity.class);
            intent.putExtra("menuId", selectedItem.getId());
            intent.putExtra("imageView01", selectedItem.getImageId());
            intent.putExtra("textView01", selectedItem.getTitle());
            intent.putExtra("priceTitle", selectedItem.getPrice());
            intent.putExtra("textView001", selectedItem.getText1());
            intent.putExtra("textView002", selectedItem.getText2());
            intent.putExtra("textView003", selectedItem.getText3());
            intent.putExtra("textView004", selectedItem.getText4());
            intent.putExtra("position", position); // Передаем позицию для удаления
            startActivity(intent);
        });

        // Обработчик клика на кнопке очистки корзины
        textViewClearCart.setOnClickListener(v -> clearCart());

        // Обработчик клика на контейнере для бонусных баллов
        balanceContainer.setOnClickListener(v -> {
            Intent intent = new Intent(CartActivity.this, Bonuses.class);
            startActivity(intent);
        });

        container.setOnClickListener(v -> {
            Intent intent = new Intent(CartActivity.this, MakingOrder.class);
            startActivity(intent);
        });

        if (getIntent().getBooleanExtra("clearCart", false)) {
            clearCart();
        }

        updateCart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateCart();
    }

    private void navigateToActivity(Class<?> activityClass) {
        Intent intent = new Intent(CartActivity.this, activityClass);
        startActivity(intent);
        finish();
    }

    private void updateCart() {
        orderedItemsInfo.clear();
        List<Order.OrderItem> orderedItems = Order.orderItems;
        int totalSum = 0;

        for (Order.OrderItem item : orderedItems) {
            String itemInfo = item.getTitle() + " - " + item.getPrice();
            orderedItemsInfo.add(itemInfo);

            try {
                int price = Integer.parseInt(item.getPrice().replaceAll("[^\\d]", ""));
                totalSum += price;
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        adapter.notifyDataSetChanged();

        textView38.setText(totalSum + " ₽");
        int bonusPoints = (int) (totalSum * 0.05);
        textView39.setText(String.valueOf(bonusPoints));

        // Устанавливаем стоимость доставки
        int deliveryCost = 200;
        if (totalSum >= 2000) {
            textView41.setText("0 ₽"); // Бесплатная доставка
            deliveryCost = 0;
        } else {
            textView41.setText("200 ₽"); // Доставка за 200 рублей
        }

        // Рассчитываем итоговую сумму для оформления заказа
        int finalOrderSum = totalSum + deliveryCost;
        String orderText = "Оформить заказ на " + finalOrderSum + " ₽";
        textViewB.setText(orderText);

        // Обновляем видимость текстов "Корзина пуста" и списка заказов
        boolean isEmpty = orderedItemsInfo.isEmpty();
        textViewEmptyCart1.setVisibility(isEmpty ? View.VISIBLE : View.GONE);
        textViewEmptyCart2.setVisibility(isEmpty ? View.VISIBLE : View.GONE);
        ordersList.setVisibility(isEmpty ? View.GONE : View.VISIBLE);

        // Обновляем видимость кнопки очистки корзины
        textViewClearCart.setVisibility(isEmpty ? View.GONE : View.VISIBLE);

        // Обновляем видимость и текст для статуса корзины
        if (!isEmpty) {
            int itemCount = orderedItems.size();
            String itemText = "В корзине " + itemCount + " " + getItemText(itemCount);
            textViewCartStatus.setText(itemText);
            textViewCartStatus.setVisibility(View.VISIBLE);
            balanceContainer.setVisibility(View.VISIBLE); // Показываем balanceContainer

            // Показываем textView38 и textView39 только если есть хотя бы один товар
            textView38.setVisibility(View.VISIBLE);
            textView39.setVisibility(View.VISIBLE);

            // Показываем все дополнительные элементы
            textView35.setVisibility(View.VISIBLE);
            textView37.setVisibility(View.VISIBLE);
            imageView34.setVisibility(View.VISIBLE);
            imageView32.setVisibility(View.VISIBLE);
            textView40.setVisibility(View.VISIBLE);
            textView41.setVisibility(View.VISIBLE);
            textView42.setVisibility(View.VISIBLE);
            textView43.setVisibility(View.VISIBLE);
            constraintLayout11.setVisibility(View.VISIBLE);
            constraintLayout12.setVisibility(View.VISIBLE);
            container.setVisibility(View.VISIBLE);
        } else {
            textViewCartStatus.setVisibility(View.GONE);
            balanceContainer.setVisibility(View.GONE);
            textView38.setVisibility(View.GONE);
            textView39.setVisibility(View.GONE);
            textViewB.setVisibility(View.GONE); // Скрываем кнопку оформления заказа, если корзина пуста

            // Скрываем все дополнительные элементы
            textView35.setVisibility(View.GONE);
            textView37.setVisibility(View.GONE);
            imageView34.setVisibility(View.GONE);
            imageView32.setVisibility(View.GONE);
            textView40.setVisibility(View.GONE);
            textView41.setVisibility(View.GONE);
            textView42.setVisibility(View.GONE);
            textView43.setVisibility(View.GONE);
            constraintLayout11.setVisibility(View.GONE);
            constraintLayout12.setVisibility(View.GONE);
            container.setVisibility(View.GONE);
        }

        Log.d("CartActivity", "Общая сумма заказа: " + totalSum + ", Бонусные баллы: " + bonusPoints);

        // Сохраняем данные корзины в SharedPreferences
        saveCartData(orderedItemsInfo);
    }
    private void clearCart() {
        // Очистка списка заказанных товаров и обновление адаптера
        Order.orderItems.clear();
        adapter.notifyDataSetChanged();

        // Очистка данных в SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("CartData", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();

        updateCart(); // Обновляем интерфейс корзины после очистки
    }

    private void placeOrder() {
        // Реализация логики оформления заказа
        if (Order.orderItems.isEmpty()) {
            // Показываем сообщение пользователю о том, что корзина пуста
            textViewEmptyCart1.setText("Корзина пуста. Добавьте товары для оформления заказа.");
            textViewEmptyCart1.setVisibility(View.VISIBLE);
            textViewEmptyCart2.setVisibility(View.VISIBLE);
            return;
        }

        // Очистка корзины после оформления заказа
        clearCart();
        textViewEmptyCart1.setText("Ваш заказ был успешно оформлен!");
        textViewEmptyCart1.setVisibility(View.VISIBLE);
        textViewEmptyCart2.setVisibility(View.GONE);
    }

    private String getItemText(int itemCount) {
        if (itemCount == 1) {
            return "блюдо";
        } else if (itemCount >= 2 && itemCount <= 4) {
            return "блюда";
        } else {
            return "блюд";
        }
    }

    private void setupConstraintLayoutListeners() {
        ConstraintLayout constraintLayout11 = findViewById(R.id.constraintLayout11);
        constraintLayout11.setOnClickListener(v -> {
            Order.OrderItem item = new Order.OrderItem(
                    3,
                    R.drawable.quesadillia,
                    "Кесадилия",
                    "250 ₽",
                    "Кесадилия",
                    "Кесадилья - исконное мексиканское блюдо, воплощающее гармонию вкусов и текстур. В основе - мягкая тортилья, наполненная расплавленным сыром, курицей и овощами. Тортилью складывают пополам и обжаривают до золотистой корочки, достигая идеального баланса хруста и нежности.",
                    "Состав (250 г):",
                    "Две полузакрытые тортильи с курицей, сыром чеддер, болгарским перцем и цукини в начинке."
            );
            navigateToInformationActivity(item);
        });

        ConstraintLayout constraintLayout12 = findViewById(R.id.constraintLayout12);
        constraintLayout12.setOnClickListener(v -> {
            Order.OrderItem item = new Order.OrderItem(
                    2,
                    R.drawable.chimiching,
                    "Чимичанга",
                    "470 ₽",
                    "Чимичанга",
                    "Чимичанга - яркое блюдо мексиканской кухни, завоевавшее сердца гурманов по всему миру. В основе - хрустящая, золотистая тортилья, обжаренная до совершенства. Внутри - сочная смесь из мяса, бобов, сыра и ароматных специй. Чимичангу подают с острым соусом и свежей сальсой. Это настоящий праздник вкуса для любителей насыщенных и пикантных блюд, дарящий незабываемые гастрономические впечатления!",
                    "Состав (350 г):",
                    "Закрытая тортилья с добавлением кус куса, фасоли, кукурузы, сыра чеддера, соуса пико де гайо и мелкорубленного мяса (говядина)"
            );
            navigateToInformationActivity(item);
        });

        ConstraintLayout Container = findViewById(R.id.Container);
        textViewB = Container.findViewById(R.id.textViewB);


    }

    private void navigateToInformationActivity(Order.OrderItem item) {
        Intent intent = new Intent(CartActivity.this, InformationActivity.class);
        intent.putExtra("menuId", item.getId());
        intent.putExtra("imageView01", item.getImageId());
        intent.putExtra("textView01", item.getTitle());
        intent.putExtra("priceTitle", item.getPrice());
        intent.putExtra("textView001", item.getText1());
        intent.putExtra("textView002", item.getText2());
        intent.putExtra("textView003", item.getText3());
        intent.putExtra("textView004", item.getText4());
        startActivity(intent);
    }

    private void saveCartData(List<String> orderedItemsInfo) {
        SharedPreferences sharedPreferences = getSharedPreferences("CartData", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Set<String> orderedItemsSet = new HashSet<>(orderedItemsInfo);
        editor.putStringSet("orderedItems", orderedItemsSet);
        editor.apply();
    }
}