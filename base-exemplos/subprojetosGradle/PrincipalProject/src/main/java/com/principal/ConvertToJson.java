package com.principal;

import com.model.ContatoModel;
import com.util.GsonUtil;

public class ConvertToJson {
	public static void main(String[] args) {
		ContatoModel contatoModel = new ContatoModel("Aluno 1", "aluno1.inatel.br", "(35)9999-0000");

		String json = GsonUtil.toJson(contatoModel);

		System.out.println(json);
	}

}
