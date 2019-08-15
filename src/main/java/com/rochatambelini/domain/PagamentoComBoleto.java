package com.rochatambelini.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;

import com.rochatambelini.domain.enums.EstadoPagamento;

@Entity
public class PagamentoComBoleto extends Pagamento implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Date dataPagamento;
	private Date dateVencimento;

	
	public PagamentoComBoleto() {
		
	}


	public PagamentoComBoleto(Integer id, EstadoPagamento estado, Pedido pedido,Date dataPagamento,Date dateVencimento) {
		super(id, estado, pedido);
		this.dataPagamento = dataPagamento;
		this.dateVencimento = dateVencimento;
	}


	public Date getDataPagamento() {
		return dataPagamento;
	}


	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}


	public Date getDateVencimento() {
		return dateVencimento;
	}


	public void setDateVencimento(Date dateVencimento) {
		this.dateVencimento = dateVencimento;
	}
	
	
	
	
}
