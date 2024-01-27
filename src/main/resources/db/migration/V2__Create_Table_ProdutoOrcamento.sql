CREATE TABLE produtoOrcamento (
    id INT AUTO_INCREMENT,
    orcamento_id INT NOT NULL,
    nome VARCHAR(200) NOT NULL,
    valor FLOAT NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY (orcamento_id) REFERENCES orcamento(id)
);
