package br.com.codiub.feti.repository;

import br.com.codiub.feti.model.output.GetQtdFuncaoByEditalOutput;
import br.com.codiub.feti.model.output.QuantidadeInscricaoOutput;
import br.com.codiub.feti.model.output.QuantidadeAllOutput;
import br.com.codiub.feti.model.output.QuantidadeUserByMesOutput;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class DashboardRepository  {
    @PersistenceContext
    private EntityManager entityManager;
    public List<QuantidadeInscricaoOutput> getTotalInscricoes() {
        String sql = "SELECT " +
                "    CAST(COUNT(i.edital) AS INTEGER) AS qtd, " +
                "    e.edital " +
                "FROM " +
                "    public.inscricao AS i " +
                "INNER JOIN " +
                "    public.editais AS e " +
                "        ON i.edital = e.id " +
                "WHERE " +
                "    e.actived = true AND i.actived = true " +
                "GROUP BY " +
                "    e.edital";

        return (List<QuantidadeInscricaoOutput>) entityManager.createNativeQuery(sql)
                .unwrap(NativeQuery.class)
                .setResultTransformer(Transformers.aliasToBean(QuantidadeInscricaoOutput.class))
                .getResultList();
    }

    public List<QuantidadeUserByMesOutput> getQuantidadeUsuariosByMes() {
        String sql = "SELECT " +
                "    TO_CHAR(created, 'MM/YYYY') AS mes_ano, " +
                "    CAST(COUNT(*) AS INTEGER)  AS qtd " +
                "FROM " +
                "    public.users " +
                "WHERE " +
                "     actived = true " +
                "GROUP BY " +
                "    TO_CHAR(created, 'MM/YYYY') " +
                "ORDER BY " +
                "    mes_ano ";

        return (List<QuantidadeUserByMesOutput>) entityManager.createNativeQuery(sql)
                .unwrap(NativeQuery.class)
                .setResultTransformer(Transformers.aliasToBean(QuantidadeUserByMesOutput.class))
                .getResultList();
    }

    public QuantidadeAllOutput getQuantidadeAll() {
        String sql = "SELECT " +
                "  (SELECT CAST(COUNT(*) AS INTEGER) FROM public.users WHERE actived = true) AS qtd_usuarios, " +
                "  (SELECT CAST(COUNT(*) AS INTEGER) FROM public.editais WHERE actived = true) AS qtd_editais, " +
                "  (SELECT CAST(COUNT(*) AS INTEGER) FROM public.inscricao WHERE actived = true) AS qtd_inscricoes ";

        return (QuantidadeAllOutput) entityManager.createNativeQuery(sql)
                .unwrap(NativeQuery.class)
                .setResultTransformer(Transformers.aliasToBean(QuantidadeAllOutput.class))
                .getSingleResult();
    }

    public List<GetQtdFuncaoByEditalOutput> getQtdFuncaoByEdital(Long id){
        String sql = "SELECT " +
                "    e.edital AS edital, " +
                "    f.funcao AS funcao, " +
                "    CAST(COUNT(i.id) AS INTEGER) AS quantidade_inscritos " +
                "FROM " +
                "    public.editais e " +
                "INNER JOIN " +
                "    public.inscricao i ON e.id = i.edital " +
                "INNER JOIN " +
                "    public.funcoes f ON i.funcao = f.id " +
                "WHERE " +
                "    e.id = " + id + " " +
                "    AND e.actived = true " +
                "    AND i.actived = true " +
                "    AND f.actived = true " +
                "GROUP BY " +
                "    e.edital, f.funcao " +
                "ORDER BY " +
                "    e.edital, f.funcao";

        return (List<GetQtdFuncaoByEditalOutput>) entityManager.createNativeQuery(sql)
                .unwrap(NativeQuery.class)
                .setResultTransformer(Transformers.aliasToBean(GetQtdFuncaoByEditalOutput.class))
                .getResultList();
    }
}
