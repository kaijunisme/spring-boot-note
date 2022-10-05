package com.example.note.po;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * po 基礎類別
 *
 * @Data 等同於@Getter/@Setter/@ToString/@EqualsAndHashCode/@RequiredArgsConstructor
 * @MapperedSupperclass 宣告為實體類的父類別，它不是完整的實體類，並不會映射到單獨的資料表，只有它的子類別會將它的屬性映射到資料表。
 * @EntityListeners 攔截實體類存取資料庫的操作。
 */
@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@DynamicInsert
@DynamicUpdate
public class BasePo {

    /**
     * 建立者
     * @CreateBy 設定建立者，當實體被insert 時會預設值。
     * @Column 設定對應資料表欄位名稱
     */
    @CreatedBy
    @Column(name = "CREATE_BY", updatable = false)
    private String createBy;

    /**
     * 建立時間
     * @CreateDate 設定為建立時間，當實體被insert 時會預設值。
     * @Temporal 設定儲存至資料表的時間精度，僅適用於Date 或Calendar 物件。
     *  TemporalType.DATE 日期
     *  TemporalType.TIME 時間
     *  TemporalType.TIMESTAMP 日期和時間
     * @Column 設定對應資料表欄位名稱
     */
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATE_TIME", updatable = false)
    private Date createTime;

    /**
     * 修改者
     * @UpdateBy 設定修改者，當實體被update 時會預設值。
     * @Column 設定對應資料表欄位名稱
     */
    @LastModifiedBy
    @Column(name = "UPDATE_BY")
    private String updateBy;

    /**
     * 修改時間
     * @LastModifiedDate 設定修改立時間，當實體被update 時會預設值。
     * @Temporal 設定儲存至資料表的時間精度，僅適用於Date 或Calendar 物件。
     *  TemporalType.DATE 日期
     *  TemporalType.TIME 時間
     *  TemporalType.TIMESTAMP 日期和時間
     * @Column 設定對應資料表欄位名稱
     */
    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATE_TIME")
    private Date updateTime;

}
