USE monitoramento;

CREATE TABLE IF NOT EXISTS MonitoramentoCPU(
idLeitura INT PRIMARY KEY AUTO_INCREMENT,
UsoCPU DOUBLE,
horario DATETIME DEFAULT CURRENT_TIMESTAMP
);

SELECT * FROM MonitoramentoCPU;