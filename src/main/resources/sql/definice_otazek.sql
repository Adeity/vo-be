-- uprava answer tabulky
alter table answer drop column question_definition_id;
alter table answer add column question_definition_id int;
ALTER TABLE answer
    ADD CONSTRAINT answer_question_id_fk
        FOREIGN KEY (question_definition_id)
            REFERENCES question (id);
alter table answer alter column question_definition_id set not null;

-- uprava submitted form, ze nepotrebuje research participant
alter table submitted_form alter column research_participant_id drop not null;

alter table question drop column type;
alter table question drop column value;

alter table submitted_form drop column respondent_identifier;
-- psqi code: 1xx
insert into question(id, code, label)
values
    (101, 'psqiq1', 'V kolik hodin jste obvykle během posledního měsíce večer ulehl(a) do postele? (hh:mm)'),
    (102, 'psqiq2', 'Jak dlouho (v minutách) vám obvykle každý večer během posledního měsíce trvalo než jste usnul(a)?'),
    (103, 'psqiq3', 'V kolik hodin jste obvykle během posledního měsíce ráno vstával(a) z postele? (hh:mm)'),
    (104, 'psqiq4', 'Kolik hodin za noc jste minulý měsíc obvykle opravdu spal(a)? (To se může lišit od počtu strávených v posteli.)'),
    (105, 'psqiq5a', 'Jak často: Nemohl jste usnout do 30 min'),
    (106, 'psqiq5b', 'Jak často: Vzbudil(a) jste se uprostřed noci nebo brzy ráno.'),
    (107, 'psqiq5c', 'Jak často: Musel(a) jste vstávat a jít na záchod.'),
    (108, 'psqiq5d', 'Jak často: Nemohl(a) jste dobře dýchat.'),
    (109, 'psqiq5e', 'Jak často: Hlasitě jste kašlal(a) nebo chrápal(a).'),
    (110, 'psqiq5f', 'Jak často: Bylo Vám příliš chladno.'),
    (111, 'psqiq5g', 'Jak často: Bylo Vám příliš horko.'),
    (112, 'psqiq5h', 'Jak často: Měl(a) jste špatné sny'),
    (113, 'psqiq5i', 'Jak často: Měl(a) jste bolesti.'),
    (114, 'psqiq5j', 'Prosím, popište jiné důvody, kvůli kterým jste se musel(a) vzbudit. a jak často v týdnu.'),
    (115, 'psqiq6', 'Jak byste celkově ohodnotil(a) kvalitu svého spánku během posledního měsíce?'),
    (116, 'psqiq7', 'Kolikrát jste během posledního měsíce užil(a) léky nebo jiné přípravky, které vám pomáhají usnout a spát (na lékařský předpis nebo bez předpisu + jaké to byly?'),
    (117,'psqiq8', 'Jak často jste se během minulého měsíce cítil(a) ospalý/á při řízení auta, při jídle nebo při jiné společenské činnosti?'),
    (118, 'psqiq9', 'Jak těžké bylo pro vás během posledního měsíce udržet si dostatek elánu pro dokončení činností?'),
    (119, 'psqiq10', 'Spí ve vašem bytě nebo ve vaší posteli ještě někdo jiný?'),
    (120, 'psqiFreeDaysGnt', 'O volných dnech chodím spát v (hh:mm)'),
    (121, 'psqiFreeDaysGmt', 'O volných dnech vstávám v (hh:mm)'),
    (122, 'psqiWorkDaysGnt', 'O pracovních dnech chodím spát v (hh:mm)'),
    (123, 'psqiWorkDaysGmt', 'O pracovních dnech vstávám v (hh:mm)');

insert into question(id, code, label)
values
    (200, 'dzshealth0', 'Se svým tělesným zdravotním stavem jsem...'),
    (201, 'dzshealth1', 'Se svou duševní kondicí jsem...'),
    (202, 'dzshealth2', 'Se svou tělesnou kondicí jsem...'),
    (203, 'dzshealth3', 'Se svou duševní výkonností jsem...'),
    (204, 'dzshealth4', 'Se svou obranyschopností proti nemoci jsem...'),
    (205, 'dzshealth5', 'Když myslím na to, jak často mám bolesti, jsem...'),
    (206, 'dzshealth6', 'Když myslím na to, jak často jsem až dosud byl(a) nemocný(á), jsem...'),
    (207, 'dzswork0', 'Se svým postavením na pracovišti jsem...'),
    (208, 'dzswork1', 'Když myslím na to, jak jistá je moje budoucnost v zaměstnání, jsem...'),
    (209, 'dzswork2', 'S úspěchy, které mám v zaměstnání, jsem...'),
    (210, 'dzswork3', 'S možnostmi postupu, které mám v zaměstnání, jsem...'),
    (211, 'dzswork4', 'S atmosférou na pracovišti jsem...'),
    (212, 'dzswork5', 'Co se týká mých pracovních povinností a zátěže, jsem...'),
    (213, 'dzswork6', 'S pestrostí, kterou mi nabízí mé zaměstnání, jsem...'),
    (214, 'dzsfinances0', 'Se svým příjmem/platem jsem...'),
    (215, 'dzsfinances1', 'S tím, co vlastním, jsem...'),
    (216, 'dzsfinances2', 'Se svým životním standardem jsem...'),
    (217, 'dzsfinances3', 'S hmotným zajištěním své existence jsem...'),
    (218, 'dzsfinances4', 'Se svými budoucími možnostmi výdělku jsem...'),
    (219, 'dzsfinances5', 'S možnostmi, které mohu vzhledem ke své finanční situaci nabídnout své rodině jsem...'),
    (220, 'dzsfinances6', 'Se svým budoucím očekávaným (finančním) zajištěním ve stáří jsem...'),
    (221, 'dzsfreeTime0', 'S délkou své každoroční dovolené jsem...'),
    (222, 'dzsfreeTime1', 'S množstvím svého volného času po práci a o víkendech jsem...'),
    (223, 'dzsfreeTime2', 'S kvalitou odpočinku, který mi přináší dovolená, jsem...'),
    (224, 'dzsfreeTime3', 'S kvalitou odpočinku, který mi přináší volný čas po práci a víkendy, jsem...'),
    (225, 'dzsfreeTime4', 'S množstvím času, které mám k dispozici pro své koníčky, jsem...'),
    (226, 'dzsfreeTime5', 'S časem, který mohu věnovat blízkým osobám jsem...'),
    (227, 'dzsfreeTime6', 'S pestrostí svého volného času jsem...'),
    (228, 'dzspartnership0', 'S požadavky, které na mne klade mé manželství/partnerství, jsem...'),
    (229, 'dzspartnership1', 'S našimi společnými aktivitami jsem...'),
    (230, 'dzspartnership2', 'S upřímností a otevřeností svého partnera/partnerky jsem...'),
    (231, 'dzspartnership3', 'S pochopením, která má pro mne můj partner/partnerka, jsem..'),
    (232, 'dzspartnership4', 'S něžností a náklonností, kterou mi můj partner/partnerka projevuje jsem...'),
    (233, 'dzspartnership5', 'S bezpečím, které mi poskytne můj partner/partnerka, jsem...'),
    (234, 'dzspartnership6', 'S ochotou pomoci, kterou mi projevuje můj partner/partnerka, jsem...'),
    (235, 'dzschildren0', 'když myslím na to, jak s dětmi vzájemně vycházíme, jsem...'),
    (236, 'dzschildren1', 'Když myslím na úspěchy svých dětí ve škole a zaměstnání, jsem...'),
    (237, 'dzschildren2', 'Když myslím na to, kolik radosti mám ze svých dětí, jsem...'),
    (238, 'dzschildren3', 'Když myslím na námahu a výdaje, které mě mé děti stály, jsem...'),
    (239, 'dzschildren4', 'S vlivem, který mám na své děti, jsem...'),
    (240, 'dzschildren5', 'S uznáním, kterého se mi od mých dětí dostává jsem...'),
    (241, 'dzschildren6', 'S našimi společnými aktivitami jsem...'),
    (242, 'dzspersonality0', 'Se svými schopnostmi a dovednostmi jsem...'),
    (243, 'dzspersonality1', 'Se způsobem, jak jsem až doposud žil, jsem...'),
    (244, 'dzspersonality2', 'Se svým vnějším vzhledem jsem...'),
    (245, 'dzspersonality3', 'Se svým sebevědomím a sebejistotou jsem...'),
    (246, 'dzspersonality4', 'Se svým charakterem (povahou) jsem...'),
    (247, 'dzspersonality5', 'Se svou vitalitou (tzn. s radostí ze života a životní energií) jsem...'),
    (248, 'dzspersonality6', 'Když myslím na to, jak vycházím s ostatními lidmi, jsem...'),
    (249, 'dzssexuality0', 'Se svou tělesnou přitažlivostí jsem...'),
    (250, 'dzssexuality1', 'Se svou sexuální výkonností jsem...'),
    (251, 'dzssexuality2', 'S častostí svých sexuálních kontaktů jsem...'),
    (252, 'dzssexuality3', 'S tím, jak často se mi můj partner/má partnerka tělesně věnuje (dotýká se mne, hladí mne), jsem...'),
    (253, 'dzssexuality4', 'Se svými sexuálními reakcemi jsem...'),
    (254, 'dzssexuality5', 'Když myslím na to, jak otevřeně mohu mluvit o sexuální oblasti, jsem...'),
    (255, 'dzssexuality6', 'Když myslím na to, jak se k sobě s partnerem hodíme, jsem...'),
    (256, 'dzsfriends0', 'Když myslím na okruh svých přátel a známých, jsem...'),
    (257, 'dzsfriends1', 'S kontakty se svými příbuznými jsem...'),
    (258, 'dzsfriends2', 'S kontaktem se svými sousedy jsem...'),
    (259, 'dzsfriends3', 'S pomocí a podporou, kterou mi poskytují přátelé a známí, jsem...'),
    (260, 'dzsfriends4', 'Se svým veřejnými a spolkovými aktivitami jsem...'),
    (261, 'dzsfriends5', 'Se svou společenskou angažovaností jsem...'),
    (262, 'dzsfriends6', 'Když myslím na to, jak často se dostanu mezi lidi, jsem...'),
    (263, 'dzshabitation0', 'S velikostí svého bytu jsem...'),
    (264, 'dzshabitation1', 'Se stavem svého bytu jsem...'),
    (265, 'dzshabitation2', 'S výdaji za svůj byt (nájem, příp. splátky) jsem...'),
    (266, 'dzshabitation3', 'S polohou svého bytu jsem...'),
    (267, 'dzshabitation4', 'S dosažitelností dopravních prostředků jsem...'),
    (268, 'dzshabitation5', 'Když myslím na míru zátěže hlukem ve vlastním bytě...'),
    (269, 'dzshabitation6', 'Se standardem svého bytu jsem...'),
    (290, 'dzsDoYouHaveAPartner', 'Máte v současné době stálého partnera/partnerku?'),
    (291, 'dzsDoYouHaveKids', 'Máte vlastní děti?');

insert into question(id, code, label)
values
    (300, 'pssQ0', 'Jak často jste v posledním měsíci byl/a rozrušen/a něčím neočekávaným?'),
    (301, 'pssQ1', 'Jak často jste v posdlením měsíci měl/a pocit, že nemáte kontrolu nad důležitými věcmi ve svém životě?'),
    (302, 'pssQ2', 'Jak často jste se v posledním měsíci cítil/a nervózní a ve stresu?'),
    (303, 'pssQ3', 'Jak často jste v posledním měsíci věřil/a, že dokážete sebejistě zvládat své osobní problémy?'),
    (304, 'pssQ4', 'Jak často Vam v posledním měsíci přišlo, že jdou věci podle plánu?'),
    (305, 'pssQ5', 'Jak často jste v posledním měsíci zjistil/a, že nezvládáte všechny věci, které musíte udělat?'),
    (306, 'pssQ6', 'Jak často jste v posledním měsíci cítil/a, že dokážete kontrolovat nepříjemné situace ve svém životě?'),
    (307, 'pssQ7', 'Jak často jste v posledním měsíci cítil/a, že máte věci pod kontrolou?'),
    (308, 'pssQ8', 'Jak často jste byl/a v posledním měsíci rozzlobený/á kvůli věcem, které jste nemohl/a ovlivnit?'),
    (309, 'pssQ9', 'Jak často jste v posledním měsíci cítil/a, že se potíže hromadí tak moc, že je nedokážete zvládnout?');

insert into question (id, code, label)
values
    (401, 'meqQ1', 'Vezmete-li v úvahu pouze to, při jakém denním rytmu se cítíte nejlépe, v kolik hodin byste vstávali, pokud byste si mohli zcela svobodně naplánovat svůj den?'),
    (402, 'meqQ2', 'Vezmete-li v úvahu pouze to, při jakém denním rytmu se cítíte nejlépe, v kolik hodin byste šli spát, pokud byste si mohli zcela svobodně naplánovat svůj večer?'),
    (403, 'meqQ3', 'Pokud ráno musíte vstávat v určitou dobu, do jaké míry jste závislý/á na zvonění budíku?'),
    (404, 'meqQ4', 'Jak snadno se vám ráno vstává v přiměřených podmínkách prostředí? (v případě, že Vás nic nečekaně neprobudí?)'),
    (405, 'meqQ5', 'Jak čilý/á se cítíte během první půl hodiny po ranním probuzení?'),
    (406, 'meqQ6', 'Jakou máte chuť k jídlu během první půl hodiny po ranním probuzení?'),
    (407, 'meqQ7', 'Jak moc se cítíte unavený/á během první půl hodiny po ranním probuzení?'),
    (408, 'meqQ8', 'Nemáte-li další den žádné povinnosti, kdy půjdete spát ve srovnání s dobou, kdy obvykle chodíte do postele?'),
    (409, 'meqQ9', 'Rozhodl/a jste se začít pravidelně cvičit. Váš přítel navrhuje, že spolu budete cvičit 2x týdně jednu hodinu. Nejvíce mu vyhovuje čas mezi 7. až 8. hodinou ráno. S ohledem na denní rytmus, při kterém se cítíte nejlépe, jaký výkon byste podle vás podal/a?'),
    (410, 'meqQ10', 'V kolik hodin večer cítíte únavu a cítíte tedy potřebu jít spát?'),
    (411, 'meqQ11', 'Přejete si podat co nejlepší výkon v testu, o kterém víte, že je mentálně vyčerpávající a trvá dvě hodiny. Pokud byste mohl/a zcela svobodně plánovat svůj den, s ohledem na rytmus, při kterém se cítíte nejlépe, který ze čtyř časů testu byste si vybral/a?'),
    (412, 'meqQ12', 'Pokud jdete spát ve 23 hodin, jak moc se cítíte unavený/á?'),
    (413, 'meqQ13', 'Z nějakého důvodu jste šel/šla spát o několik hodin později než obvykle, ale další den ráno nemusíte vstávat v určitou dobu. Kterou z následujících situací nejpravděpodobněji zažijete?'),
    (414, 'meqQ14', 'Jednu noc musíte být vzhůru mezi 4. až 6. hodinou ráno, abyste provedl/a noční hlídku. Další den nemáte žádné povinnosti. Která z následujících možností by vám nejvíce vyhovovala?'),
    (415, 'meqQ15', 'Budete muset dvě hodiny tvrdě fyzicky pracovat. Máte úplnou volnost v plánování svého dne. S ohledem na denní rytmus, při kterém se cítíte nejlépe, které z následujících časových rozmezí byste si vybral/a?'),
    (416, 'meqQ16', 'Rozhodl/a jste se začít s těžkým tělesným cvičením. Váš přítel navrhuje, že spolu budete cvičit 2x týdně jednu hodinu. Nejvíce mu vyhovuje čas mezi 22. a 23. hodinou večer. S ohledem na denní rytmus, při kterém se cítíte nejlépe, jaký výkon byste podle vás podal/a?'),
    (417, 'meqQ17', 'Předpokládejte, že si můžete vybrat pracovní dobu. Dále předpokládejte, že pracujete pět hodin denně (včetně přestávek), Vaše práce je zajímavá a placená podle výsledků. V kolik hodin by začínala vaše pětihodinová směna?'),
    (418, 'meqQ18', 'V kolik hodin během dne se cítíte nejlépe, na vrcholu svých sil?'),
    (419, 'meqQ19', 'Zřejmě jste už slyšel/a o „ranních“ a „večerních“ typech lidí („ranní ptáčata“ a „noční sovy“). Za který z těchto typů se považujete?');

insert into question (id, code, label)
values
    (501, 'demoQ1', 'Zadejte prosím svůj věk.'),
    (502, 'demoQ2', 'Jaké je vaše pohlaví?'),
    (503, 'demoQ3', 'Jste převážně student, nebo převážně pracující?'),
    (504, 'demoQ4', 'Jaké je vaše nejvyšší dosažené vzdělání?'),
    (505, 'demoQ5', 'Zadejte prosím svou výšku v centimentrech.'),
    (506, 'demoQ6', 'Zadejte prosím svou váhu v kilogramech.'),
    (507, 'demoQ7', 'Jaký je Váš rodinný stav?');

insert into question (id, code, label)
values
    (601, 'mctqQ1', 'Pracovní dný: V kolik hodin chodíte do postele?'),
    (602, 'mctqQ2', 'Pracovní dný: V kolik hodin jste připravený usnout? (vemte v úvahu, jak dlouho vám tvrá usnout)'),
    (603, 'mctqQ3', 'Pracovní dný: Kolik minut Vám po ulehnutí trvá, než usnete?'),
    (604, 'mctqQ4', 'Pracovní dný: V kolik hodin se probouzíte?'),
    (605, 'mctqQ5', 'Pracovní dný: Vstáváte s budíkem, či nikoli?'),
    (606, 'mctqQ6', 'Pracovní dný: Kolik minut po probuzení vstáváte z postele?'),
    (607, 'mctqQ7', 'Pracovní dný: Kolik průměrně trávíte během dne času na denním světle (bez střechy nad hlavou)?'),
    (608, 'mctqQ8', 'Volné dne: V kolik hodin chodíte do postele?'),
    (609, 'mctqQ9', 'Volné dne: V kolik hodin jste připravený usnout? (vemte v úvahu, jak dlouho vám tvrá usnout)'),
    (610, 'mctqQ10', 'Volné dne: Kolik minut Vám po ulehnutí trvá, než usnete?'),
    (611, 'mctqQ11', 'Volné dne: V kolik hodin se probouzíte?'),
    (612, 'mctqQ12', 'Volné dne: Vstáváte s budíkem, či nikoli?'),
    (613, 'mctqQ13', 'Volné dne: Kolik minut po probuzení vstáváte z postele?'),
    (614, 'mctqQ14', 'Volné dne: Kolik průměrně trávíte během dne času na denním světle (bez střechy nad hlavou)?')