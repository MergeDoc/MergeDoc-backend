package com.mergedoc.backend.unit.repository;

import com.mergedoc.backend.unit.entity.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Table;


@Repository
@RequiredArgsConstructor
public class UnitRepository {

    private final EntityManager em;

    //저장

    //public void saveSingleUnit(SingleUnit singleUnit){em.persist(singleUnit);}

    public void saveDoubleUnit(DoubleUnit doubleUnit){em.persist(doubleUnit);}

    public void saveMergedUnit(MergedUnit mergedUnit){em.persist(mergedUnit);}

    public void saveMappedUnit(MappedUnit mappedUnit){em.persist(mappedUnit);}

    public void saveTag(Tag tag){em.persist(tag);}

    public void saveUnitTag(UnitTag unitTag){em.persist(unitTag);}

    //조회
    /*public SingleUnit findSingleUnit(Long singleUnitId){
        return em.find(singleUnitId);
    }*/
    public DoubleUnit findDoubleUnit(Long doubleUnitId){
        return em.find(DoubleUnit.class, doubleUnitId);
    }

    public MergedUnit findMergedUnit(Long mergedUnitId){
        return em.find(MergedUnit.class, mergedUnitId);
    }

    public MappedUnit findMappedUnit(Long mappedUnitId){
        return em.find(MappedUnit.class, mappedUnitId);
    }

    public Tag findTag(Long tagId){
        return em.find(Tag.class, tagId);
    }

    public UnitTag findUnitTag(Long unitTagId){
        return em.find(UnitTag.class,unitTagId);
    }

    //삭제
    //public void deleteSingleUnit(SingleUnit singleUnit){em.remove(singleUnit);}

    public void deleteDoubleUnit(DoubleUnit doubleUnit){em.remove(doubleUnit);}

    public void deleteMergedUnit(MergedUnit mergedUnit){em.remove(mergedUnit);}

    public void deleteMappedUnit(MappedUnit mappedUnit){em.remove(mappedUnit);}

    public void deleteTag(Tag tag){em.remove(tag);}

    public void deleteUnitTag(UnitTag unitTag){em.remove(unitTag);}

}
