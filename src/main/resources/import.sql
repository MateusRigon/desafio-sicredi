INSERT INTO associado(id,cpf,nome) VALUES (1,"4324325212","mateus");

INSERT INTO pauta(id,descricao,nome,status,tempo_sessao) VALUES (2,"pautaTesteDescricao","pautaTeste",false,1);

INSERT INTO voto(id,associado_id,pauta_id,resultado) VALUES (3,1,2,"Sim");
