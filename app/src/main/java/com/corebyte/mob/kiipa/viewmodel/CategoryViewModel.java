package com.corebyte.mob.kiipa.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.corebyte.mob.kiipa.dao.CategoryDao;
import com.corebyte.mob.kiipa.model.Category;
import com.corebyte.mob.kiipa.repo.CategoryCrudOperation;

import java.util.List;

public class CategoryViewModel extends AndroidViewModel {

    private LiveData<List<Category>> categoryList;

    public CategoryViewModel(@NonNull Application application) {
        super(application);

        CategoryCrudOperation crudOperation = new CategoryCrudOperation(application);
        categoryList = ((CategoryDao)crudOperation.getAsync().getDao()).getAll();
    }

    public LiveData<List<Category>> getCategoryList() { return categoryList; }
}
