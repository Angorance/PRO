package entitites.test;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "recurrence", schema = "public", catalog = "moneythoring")
public class RecurrenceEntity {
    private int id;
    private int gap;
    private Date nextdate;
    private IotransactionEntity iotransactionByIotransactionId;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "gap", nullable = false)
    public int getGap() {
        return gap;
    }

    public void setGap(int gap) {
        this.gap = gap;
    }

    @Basic
    @Column(name = "nextdate", nullable = false)
    public Date getNextdate() {
        return nextdate;
    }

    public void setNextdate(Date nextdate) {
        this.nextdate = nextdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RecurrenceEntity that = (RecurrenceEntity) o;

        if (id != that.id) return false;
        if (gap != that.gap) return false;
        if (nextdate != null ? !nextdate.equals(that.nextdate) : that.nextdate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + gap;
        result = 31 * result + (nextdate != null ? nextdate.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "iotransaction_id", referencedColumnName = "id", nullable = false)
    public IotransactionEntity getIotransactionByIotransactionId() {
        return iotransactionByIotransactionId;
    }

    public void setIotransactionByIotransactionId(IotransactionEntity iotransactionByIotransactionId) {
        this.iotransactionByIotransactionId = iotransactionByIotransactionId;
    }
}