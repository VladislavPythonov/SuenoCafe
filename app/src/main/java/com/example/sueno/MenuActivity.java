package com.example.sueno;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sueno.adapter.CategoryAdapter;
import com.example.sueno.adapter.MenuAdapter;
import com.example.sueno.model.Category;
import com.example.sueno.model.Menu;

import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends AppCompatActivity {

    RecyclerView categoryRecycler, menuRecycler;
    CategoryAdapter categoryAdapter;
    static MenuAdapter menuAdapter;
    static List<Menu> fullMenuList = new ArrayList<>();
    static List<Menu> filteredMenuList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        ImageView imageView20 = findViewById(R.id.imageView20);
        imageView20.setOnClickListener(v -> {
            Intent intent = new Intent(MenuActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });

        ImageView imageView22 = findViewById(R.id.imageView22);
        imageView22.setOnClickListener(v -> {
            Intent intent = new Intent(MenuActivity.this, StocksActivity.class);
            startActivity(intent);
            finish();
        });

        List<Category> categoryList = createCategories();
        createMenu();

        setCategoryRecycler(categoryList);
        setMenuRecycler(filteredMenuList);

        EditText searchEditText = findViewById(R.id.searchEditText);
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    private List<Category> createCategories() {
        List<Category> categoryList = new ArrayList<>();
        categoryList.add(new Category(1, "Меню"));
        categoryList.add(new Category(2, "Горячее"));
        categoryList.add(new Category(3, "Гарниры"));
        categoryList.add(new Category(4, "Десерты"));
        categoryList.add(new Category(5, "Напитки"));
        categoryList.add(new Category(6, "Салаты"));
        categoryList.add(new Category(7, "Супы"));
        categoryList.add(new Category(8, "Смузи"));
        categoryList.add(new Category(9, "Закуски"));
        categoryList.add(new Category(10, "Тортильи"));
        return categoryList;
    }

    private void createMenu() {
        fullMenuList.clear();
        fullMenuList.add(new Menu(1, "burrito", "Буррито", "400 ₽", "Буррито", "Бурритто - это самое популярное блюдо Мексики, любимое еще древними Ацтеками. В его основе - нежная тортилья, особая мексиканская лепешка с различными добавками (томатами, сыром и специями) . В нее заворачивают кусочки мяса и овощей и поливают все это соусом.\n" + "Излюбленное блюдо ценителей мексиканской кухни, коронный номер. После страстной, горячей сальсы вкуснейшее бурито утолит ваш голод!", "Состав (350 г):", "Закрытая тортилья с добавлением кус куса, фасоли, кукурузы, сыра чеддера, соуса пико де гайо и мелкорубленного мяса (говядина).", "В корзину 400₽", 9));
        fullMenuList.add(new Menu(2, "chimiching", "Чимичанга", "470 ₽", "Чимичанга", "Чимичанга - яркое блюдо мексиканской кухни, завоевавшее сердца гурманов по всему миру. В основе - хрустящая, золотистая тортилья, обжаренная до совершенства. Внутри - сочная смесь из мяса, бобов, сыра и ароматных специй. Чимичангу подают с острым соусом и свежей сальсой. Это настоящий праздник вкуса для любителей насыщенных и пикантных блюд, дарящий незабываемые гастрономические впечатления!", "Состав (350 г):", "Закрытая тортилья с добавлением кус куса, фасоли, кукурузы, сыра чеддера, соуса пико де гайо и мелкорубленного мяса (говядина)", "В корзину 470₽", 9));
        fullMenuList.add(new Menu(3, "quesadillia", "Кесадилия", "250 ₽", "Кесадилия", "Кесадилья - исконное мексиканское блюдо, воплощающее гармонию вкусов и текстур. В основе - мягкая тортилья, наполненная расплавленным сыром, курицей и овощами. Тортилью складывают пополам и обжаривают до золотистой корочки, достигая идеального баланса хруста и нежности.", "Состав (250 г):", "Две полузакрытые тортильи с курицей, сыром чеддер, болгарским перцем и цукини в начинке.", "В корзину 250₽", 10));
        fullMenuList.add(new Menu(4, "wings", "Крылышки", "390 ₽", "Крылышки", "Острые крылышки, запечённые в духовке, радуют насыщенным вкусом и хрустящей корочкой. Эти крылышки, покрытые пикантной глазурью, идеально подходят для любителей острой и ароматной еды.", "Состав (150 г):", "4 острых крылышка, запеченные в духовке.", "В корзину 390₽", 2));
        fullMenuList.add(new Menu(5, "tekapia", "Телапия", "490 ₽", "Телапия", "Тилапия Веракрус - это нежное филе рыбы в пикантном соусе, которое подарит вам насыщенные вкусовые впечатления. Это блюдо сочетает в себе свежесть и остроту, идеально подходящее для любителей мексиканской кухни.", "Состав (200 г):", "Филе телапии в остром соусе, помидорами и луком.", "В корзину 490₽", 2));
        fullMenuList.add(new Menu(6, "toskado", "Тостадо", "290 ₽", "Тостадо", "Тостада с курицей и бананом - это необычное и яркое сочетание вкусов. Хрустящая основа, сочная курица и сладость банана создают уникальное блюдо, которое идеально подходит для гурманов, любящих эксперименты. Попробуйте это экзотическое угощение, чтобы открыть для себя новые грани мексиканской кухни.", "Состав (120 г):", "Большой королевский начос, на котором лежит курица, банан, помидор и листья салата.", "В корзину 290₽", 10));
        fullMenuList.add(new Menu(7, "nachos", "Начос", "170 ₽", "Начос", "Начос - это кукурузные тортилья чипсы, которые радуют сочным и пикантным вкусом, идеально подходящие для наслаждения в компании друзей или как закуска к любому приему пищи.", "Состав (150 г):", "Чипсы из кукурузной тортильи.", "В корзину 170₽", 10));
        fullMenuList.add(new Menu(8, "salatsueno", "Салат SUEÑO", "670 ₽", "Салат SUEÑO", "Салат  SUEÑO - это истинное воплощение свежести и изысканного вкуса. На фоне хрустящей пшеничной тортильи располагаются нежные креветки, плавно сливающиеся с ароматной горчично-медовой заправкой. Изумительное сочетание кукурузы, болгарского перца, сыра чеддер и сочных помидоров придают этому салату неповторимый вкус и особое очарование, завоевывая сердца настоящих гурмано.", "Состав (270 г):", "Пшеничная тортилья, салат, горчично-медовая заправка, креветки, кунжут, кукуруза, болгарский перец, сыр чеддер, помидор.", "В корзину 670₽", 6));
        fullMenuList.add(new Menu(9, "taco", "Тако", "425 ₽", "Тако", "Тако с курицей - это изысканное сочетание нежной мелкорубленной курицы, плавно сливающейся с ароматным сыром моцарелла в лепешке. Ароматные нотки тушеного болгарского перца и свежего салата, дополненные кунжутом для хрустящей текстуры, придают этому блюду неповторимый вкус и яркость..", "Состав (250 г):", "Мелкорубленная курица, сыр моцарелла, кунжут, кукуруза, тушенный болгарский перец, салат, красный лук в полузакрытой лепешке.", "В корзину 425₽", 10));
        fullMenuList.add(new Menu(10, "fahita", "Фахита", "360 ₽", "Фахита", "Фахита с курицей - это идеальное сочетание нежных кусочков курицы в сыре чеддер и тушеного болгарского перца. Ароматный соус из свежих помидоров и свежая зелень придают этому блюду особый вкус и аромат, делая каждый укус настоящим удовольствием.", "Состав (250 г):", "Кусочки мяса курицы, сыр чеддер, тушеный болгарский перец, салат, соус из свежих помидоров, зелень, чеснок и лук в полузакрытой лепешке.", "В корзину 360₽", 10));
        fullMenuList.add(new Menu(11, "acteca", "Ацтека", "400 ₽", "Ацтека", "Суп ацтека - это томатный суп, насыщенный ароматом и традиционными мексиканскими вкусами. В каждой ложке сочетается глубокий вкус говядины с хрустящим рисом и нежной моцареллой, создавая утонченное и удовлетворяющее блюдо для любого времени года.", "Состав (350 г):", "Томатный суп с говядиной, рисом, кукурузой, посыпается сыром моцарелла.", "В корзину 400₽", 7));
        fullMenuList.add(new Menu(12, "unococo", "Uno Coco", "370 ₽", "Uno Coco", "Uno Coco porfavor - это освежающий коктейль, который сочетает в себе экзотические ароматы ананасов, персиков и кокосового молока. Он представляет собой идеальное сочетание сладости и свежести, напоминающее о теплых тропических пляжах и отдыхе под пальмами.", "Состав (350 мл):", "Коктейль с ананасовыми и персиковым соками и кокосовым молоком.", "В корзину 370₽", 5));
        fullMenuList.add(new Menu(13, "bamble", "Бамбл кофе", "300 ₽", "Бамбл кофе", "Бамбл — это микс эспрессо, апельсинового сока, карамели и льда. У него яркий и сложный вкус: сначала чувствуется сладость сиропа, затем — цитрусовая кислинка и легкая кофейная горечь в конце.", "Состав (400 мл):", "Слоеный холодный кофейный коктейль из апельсинового сока, экспрессо, карамельного сиропа, льда.", "В корзину 300₽", 5));

        filteredMenuList.clear();
        filteredMenuList.addAll(fullMenuList);
    }

    private void setMenuRecycler(List<Menu> menuList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        menuRecycler = findViewById(R.id.menuRecycler);
        menuRecycler.setLayoutManager(layoutManager);
        menuAdapter = new MenuAdapter(this, menuList);
        menuRecycler.setAdapter(menuAdapter);
    }

    private void setCategoryRecycler(List<Category> categoryList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        categoryRecycler = findViewById(R.id.categoryRecycler);
        categoryRecycler.setLayoutManager(layoutManager);
        categoryAdapter = new CategoryAdapter(this, categoryList);
        categoryRecycler.setAdapter(categoryAdapter);
    }

    private void filter(String text) {
        filteredMenuList.clear();
        for (Menu item : fullMenuList) {
            if (item.getTitle().toLowerCase().contains(text.toLowerCase()) ||
                    item.getText().toLowerCase().contains(text.toLowerCase())) {
                filteredMenuList.add(item);
            }
        }
        menuAdapter.notifyDataSetChanged();
    }

    public void openShoppingCart(View view) {
        Intent intent = new Intent(this, CartActivity.class);
        startActivity(intent);
    }

    public static void restoreMenuList() {
        filteredMenuList.clear();
        filteredMenuList.addAll(fullMenuList);
        menuAdapter.notifyDataSetChanged();
    }

    public static void showMenusCategories(int category) {
        if (category == 1) {
            restoreMenuList();
        } else {
            filteredMenuList.clear();
            for (Menu c : fullMenuList) {
                if (c.getCategory() == category)
                    filteredMenuList.add(c);
            }
            menuAdapter.notifyDataSetChanged();
        }
    }
}
