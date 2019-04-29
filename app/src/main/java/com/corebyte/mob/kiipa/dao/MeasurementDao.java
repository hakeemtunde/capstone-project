package com.corebyte.mob.kiipa.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.corebyte.mob.kiipa.model.Measurement;

import java.util.List;

@Dao
public interface MeasurementDao extends BaseDao<Measurement> {

    @Override
    @Query("SELECT * FROM measurements")
    List<Measurement> getAll();

    @Override
    @Query("SELECT * FROM measurements WHERE id = :id")
    Measurement findById(long id);
}
