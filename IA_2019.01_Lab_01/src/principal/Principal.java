package principal;

import java.util.List;

import busca.informada.BuscaAStar;
import busca.naoinformada.BuscaEmLargura;
import busca.naoinformada.BuscaEmProfundidade;
import implementacao.FuncaoHeuristicaImp;
import implementacao.ProblemaImp;
import interfaces.Acao;
import interfaces.Estado;
import interfaces.FuncaoHeuristica;
import interfaces.Problema;

public class Principal {
	public static void main(String[] args) {
		 	Problema p = new ProblemaImp();
	      
	        BuscaEmProfundidade bep = new BuscaEmProfundidade();
	        BuscaEmLargura bel1 = new BuscaEmLargura();
	        BuscaEmLargura bel2 = new BuscaEmLargura();
	        FuncaoHeuristica h=  new FuncaoHeuristicaImp();
	        BuscaAStar bas = new BuscaAStar(h);
	        
	        long t0 = System.currentTimeMillis();
	        
	        List<Acao> result1 = bep.buscarIterativamente(p);
	        if (result1.get(0).getClass().getSimpleName().equals("CutOff")) System.out.println("Cutoff reached.");
	        System.out.println("");        
	        long t1 = System.currentTimeMillis();
	        
	        List<Acao> result2 = bel1.buscar(p);
	        long t2 = System.currentTimeMillis();
	        
	        List<Acao> result3 = bel2.buscar_v2_0(p);
	        long t3 = System.currentTimeMillis();
	        
	        List<Acao> result4 = bas.buscar(p);
	        long t4 = System.currentTimeMillis();
	        
	        Estado e = p.estadoInicial();
	        System.out.println("==================================");
	        System.out.println("Estado inicial: "+e);
	        System.out.println("==================================");
	        
	        List<Acao> result=result4;
	        for (int i = 0; i < result.size(); i++) {
	            System.out.println("[Passo " + i + "]: " + result.get(i));
	            Acao a = result.get(i);
	            e = a.resultado(e);
	            System.out.println("Estado resultado: "+e);
	            System.out.println("==================================");
	        }
	        
	        System.out.println("Elapsed time \n"+
	                " BProf = "+(t1-t0)+" (ms) \tNodos visitados : "+ bep.getMaxVisitSize()+"\tMax front: "+bep.getMaxFrontSize()+"\n"+
	                " BLarg = "+(t2-t1)+" (ms) \tNodos visitados : "+ bel1.getMaxVisitSize()+"\tMax front: "+bel1.getMaxFrontSize()+"\n"+
	                " BLarg_v2 = "+(t3-t2)+" (ms) \tNodos visitados : "+ bel2.getMaxVisitSize()+"\tMax front: "+bel2.getMaxFrontSize()+"\n" + 
	                " BAstr = "+(t4-t3)+" (ms) \tNodos visitados : "+ bas.getMaxVisitSize()+"\tMax front: "+bas.getMaxFrontSize());
	    }

}
