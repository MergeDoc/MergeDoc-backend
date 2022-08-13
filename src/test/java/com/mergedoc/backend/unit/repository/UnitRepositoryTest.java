package com.mergedoc.backend.unit.repository;

import com.mergedoc.backend.unit.entity.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.util.AssertionErrors.assertEquals;

@SpringBootTest
@Transactional
class UnitRepositoryTest {

    @Autowired UnitRepository unitRepository;
    Long mergedUnitId;

    // Single Unit 필요
/*
   @Test
   void 더블유닛_저장() {
       // given
       DoubleUnit doubleUnit = DoubleUnit.builder().build();
       SingleUnit singleUnit1 = SingleUnit.builder().title("single1").build();
       SingleUnit singleUnit2 = SingleUnit.builder().title("single2").build();
       MappedUnit mappedUnit1 = MappedUnit.builder()
               .originUnit(singleUnit1)
               .form(Form.Double)
               .orders(1).build();
       MappedUnit mappedUnit2 = MappedUnit.builder()
               .originUnit(singleUnit2)
               .form(Form.Double)
               .orders(2).build();
       doubleUnit.addMappedUnit(mappedUnit1);
       doubleUnit.addMappedUnit(mappedUnit2);

       // when
       unitRepository.saveDoubleUnit(doubleUnit);
       Long doubleUnitId = doubleUnit.getId();
       DoubleUnit getDoubleUnit = unitRepository.findDoubleUnit(doubleUnitId);

       // then
       assertEquals("저장된 유닛의 Form의 타입은 Double.",Form.Double,getDoubleUnit.getForm());
       assertEquals("더블 유닛을 통해 싱글 유닛의 정보를 가져옵니다.","single11",
               getDoubleUnit.getMappedUnitList().get(0).getOriginUnit().getTitle());
   }
   @Test
   void 머지드유닛_저장() {
       // given
       DoubleUnit doubleUnit = DoubleUnit.builder().title("double1").build();
       SingleUnit singleUnit1 = SingleUnit.builder().title("single1").build();
       SingleUnit singleUnit2 = SingleUnit.builder().title("single2").build();
       MappedUnit mappedUnit1 = MappedUnit.builder()
               .originUnit(singleUnit1)
               .form(Form.Double)
               .orders(1).build();
       MappedUnit mappedUnit2 = MappedUnit.builder()
               .originUnit(singleUnit2)
               .form(Form.Double)
               .orders(2).build();
       doubleUnit.addMappedUnit(mappedUnit1);
       doubleUnit.addMappedUnit(mappedUnit2);

       MergedUnit mergedUnit = MergedUnit.builder().build();
       SingleUnit singleUnit3 = SingleUnit.builder().title("single3").build();
       MappedUnit mappedUnit3 = MappedUnit.builder()
               .mergedUnit(mergedUnit)
               .originUnit(singleUnit3)
               .form(Form.Merged).build();
       MappedUnit mappedUnit4 = MappedUnit.builder()
               .mergedUnit(mergedUnit)
               .originUnit(doubleUnit)
               .form(Form.Merged).build();
       mergedUnit.addMappedUnit(mappedUnit3);
       mergedUnit.addMappedUnit(mappedUnit4);

       // when
       unitRepository.saveMergedUnit(mergedUnit);
       Long doubleUnitId = doubleUnit.getId();
       mergedUnitId = mergedUnit.getId();
       DoubleUnit getDouble = unitRepository.findDoubleUnit(doubleUnitId);
       MergedUnit getMerged = unitRepository.findMergedUnit(mergedUnitId);

       // then
       assertEquals("저장된 유닛의 Form의 타입은 Merged",Form.Merged,getMerged.getForm());
       assertEquals("Merged유닛을 통해 싱글유닛의 정보를 가져옵니다.",singleUnit3.getTitle(),
               getMerged.getMappedUnitList().get(0).getOriginUnit().getTitle());
       assertEquals("Merged유닛을 통해 더블유닛의 정보를 가져옵니다.",getDouble.getTitle(),
               getMerged.getMappedUnitList().get(1).getOriginUnit().getTitle());

       */
/**
        * 유닛 삭제
        *//*

       //unitRepository.deleteMergedUnit(mergedUnit);
   }
*/

   @Test
   void 태그_저장() {
       // given
       DoubleUnit doubleUnit = DoubleUnit.builder().title("double").build();
       Tag tag = Tag.builder().content("network").count(1).build();
       UnitTag unitTag = UnitTag.builder().tag(tag).unit(doubleUnit).build();
       unitRepository.saveTag(tag);
       unitRepository.saveDoubleUnit(doubleUnit);
       unitRepository.saveUnitTag(unitTag);

       // when
       Long unitTagId = unitTag.getId();
       UnitTag getUnitTag = unitRepository.findUnitTag(unitTagId);

       // then
       assertEquals("저장된 태그 내용은 같아야 한다.", "network",getUnitTag.getTag().getContent());
       assertEquals("저장된 유닛 내용은 같아야 한다.", "double",getUnitTag.getUnit().getTitle());
   }

}