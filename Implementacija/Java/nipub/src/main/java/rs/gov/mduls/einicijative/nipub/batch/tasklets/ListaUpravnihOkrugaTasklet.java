package rs.gov.mduls.einicijative.nipub.batch.tasklets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import rs.gov.mduls.einicijative.nipub.database.ObjaviZajednickiDokument;

public class ListaUpravnihOkrugaTasklet implements Tasklet, InitializingBean {

    private static final Logger logger = LoggerFactory.getLogger(ListaUpravnihOkrugaTasklet.class);
    @Autowired
    ObjaviZajednickiDokument procesor;
    private String semaFunc;
    private String imeFunc;
    private String dirDat;
    private String imeDat;

    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        logger.info("Објава листе управних округа...");
        procesor.obrada(semaFunc, imeFunc, dirDat, imeDat);
        return RepeatStatus.FINISHED;
    }

    public void setSemaFunc(String semaFunc) {
        this.semaFunc = semaFunc;
    }
    public void setImeFunc(String imeFunc) {
        this.imeFunc = imeFunc;
    }
    public void setDirDat(String dirDat) { this.dirDat = dirDat; }
    public void setImeDat(String imeDat) { this.imeDat = imeDat; }

    public void afterPropertiesSet() throws Exception {
        Assert.state(semaFunc != null, "Шема података недостаје");
        Assert.state(imeFunc != null, "Име функције недостаје");
        Assert.state(dirDat != null, "Каталог датотеке недостаје");
        Assert.state(imeDat != null, "Име датотеке недостаје");
    }
}
