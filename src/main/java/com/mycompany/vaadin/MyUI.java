package com.mycompany.vaadin;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import java.util.List;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@SpringUI
@Theme("mytheme")
public class MyUI extends UI {
private Grid<TestEntity> grid=new Grid<>(TestEntity.class);

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();
       
    List<TestEntity> myList=new MyData().getRecords();
    ListDataProvider<TestEntity> dataProvider = DataProvider.ofCollection(myList);
grid.setDataProvider(dataProvider);  
        
        //grid.setItems(myList);
        grid.setColumnOrder("id", "name");
        grid.setSelectionMode(SelectionMode.SINGLE);
         
        Window subWindow=new Window("Добавление данных");
            VerticalLayout subContent=new VerticalLayout();
            TextField addTxt=new TextField("Введите данные");
            subContent.addComponent(addTxt);
          
            Button btnSave=new Button("Сохранить");
            Button btnCancel=new Button("Отмена"); 
            Button btnDelOk=new Button("Ок");
            Button btnDelCancel=new Button("Отмена");
            Button addBtn = new Button("Добавить");
            Button delBtn=new Button("Удалить");
            Button editBtn=new Button("Редактировать");
            
            subContent.addComponent(btnSave);
            subContent.addComponent(btnCancel);
            
            subWindow.setHeight("300px");
            subWindow.setWidth("400px");
            subWindow.center();
            subWindow.setContent(subContent);     
            
        Window subWindowDel=new Window("Удалить запись");
            VerticalLayout subContentDel=new VerticalLayout();
            TextField delTxt=new TextField("Введите ID");
            subContentDel.addComponent(delTxt);       
            subContentDel.addComponent(btnDelOk);
            subContentDel.addComponent(btnDelCancel);
            
            subWindowDel.setHeight("300px");
            subWindowDel.setWidth("400px");
            subWindowDel.center();
            subWindowDel.setContent(subContentDel);  
            
        btnDelOk.addClickListener(a->{
            new MyData().delRecord(Integer.valueOf(delTxt.getValue()));  
                                             subWindowDel.close();       
            });
                    
        btnDelCancel.addClickListener(a->{
                subWindowDel.close();
            });
            
        delBtn.addClickListener(a->{
                    addWindow(subWindowDel);
                    subWindow.setModal(true);
            });
            
        editBtn.addClickListener(a->{          
                          });
            
        addBtn.addClickListener(a->{addWindow(subWindow);
                                        subWindow.setModal(true);});
            
        btnCancel.addClickListener(a->{subWindow.close();});
        btnSave.addClickListener(a->{new MyData().addRecord(addTxt.getValue());  
                                         subWindow.close();                                                                                       
                                    });
          
        layout.addComponents(grid, addBtn, delBtn, editBtn);
        setContent(layout);    
    }

    
    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
