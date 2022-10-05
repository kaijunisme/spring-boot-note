package com.example.note.po;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * Item Po 類
 *
 * @Data 等同於@Getter/@Setter/@ToString/@EqualsAndHashCode/@RequiredArgsConstructor
 * @Entity 宣告為實體類
 * @Table 設定對應資料表名稱
 * @DynamicInsert 動態產生insert 語句，若某屬性為null 時，不會加入到sql 語句中。
 * @DynamicUpdate 動態產生update 語句，若某屬性為null 時，不會加入到sql 語句中。
 */
@Data
@Entity
@Table(name = "COFFEE")
@DynamicInsert
@DynamicUpdate
public class CoffeePo {

    /**
     * 序號
     * @Id 設定為primary key
     * @GeneratedValue 設定欄位生成的方式
     *  GenerationType.AUTO 自動產生
     *  GenerationType.IDENTITY 資料庫維護
     * @Column 設定對應資料表欄位名稱
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COFFEE_SEQ")
    private int coffeeSeq;

    /**
     * 名稱
     * @Column
     *  name 設定對應資料表欄位名稱
     *  unique 設定該欄位資料不可重複
     *  nullable 設定該欄位是否可為空
     */
    @Column(name = "NAME", unique = true, nullable = false)
    private String name;

    /**
     * 產地
     * @Column
     *  name 設定對應資料表欄位名稱
     *  nullable 設定該欄位是否可為空
     */
    @Column(name = "PRODUCTION_PLACE", nullable = false)
    private String productionPlace;

    /**
     * 描述
     * @Column
     *  name 設定對應資料表欄位名稱
     */
    @Column(name = "DESCRIPTION")
    private String description;

}
