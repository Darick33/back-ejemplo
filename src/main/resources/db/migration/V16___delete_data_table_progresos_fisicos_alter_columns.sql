delete from progresos_fisicos where 1 = 1;
alter table progresos_fisicos
    drop column cuello;
alter table progresos_fisicos
    drop column bicep_derecho;
alter table progresos_fisicos
    change column bicep_izquierdo biceps decimal(10,2);
alter table progresos_fisicos
    drop column muslo_derecho;
alter table progresos_fisicos
    change column muslo_izquierdo muslos decimal(10,2);
alter table progresos_fisicos
    drop column antebrazo_derecho;
alter table progresos_fisicos
    change column antebrazo_izquierdo antebrazos decimal(10,2);
alter table progresos_fisicos
    drop column pantorilla_derecha;
alter table progresos_fisicos
    change column pantorilla_izquierda pantorillas decimal(10, 2);
alter table progresos_fisicos
    drop column hombro;
alter table progresos_fisicos
    add column espalda decimal(10, 2);

