package org.clulab.openie.entities

import org.clulab.odin.TextBoundMention
import org.clulab.struct.Interval
import org.clulab.openie.IETestUtils._
import org.scalatest.{ FlatSpec, Matchers }


class TestRuleBasedEntityFinder extends FlatSpec with Matchers {

  "Coordinated entities" should "be split into separate entities" in {

    val doc1 = jsonStringToDocument(""" { "sentences":[{ "raw":["This","list","contains","deteriorating","biophysical","conditions",",","low","inorganic","fertilizer","use",",","less","water",",","and","growing","farm","sizes"], "words":["This","list","contains","deteriorating","biophysical","conditions",",","low","inorganic","fertilizer","use",",","less","water",",","and","growing","farm","sizes"], "startOffsets":[0,5,10,19,33,45,55,57,61,71,82,85,87,92,97,99,103,111,116], "endOffsets":[4,9,18,32,44,55,56,60,70,81,85,86,91,97,98,102,110,115,121], "tags":["DT","NN","VBZ","VBG","JJ","NNS",",","JJ","JJ","NN","NN",",","JJR","NN",",","CC","VBG","NN","NNS"], "lemmas":["this","list","contain","deteriorate","biophysical","condition",",","low","inorganic","fertilizer","use",",","less","water",",","and","grow","farm","size"], "entities":["O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O"], "norms":["O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O"], "chunks":["B-NP","I-NP","B-VP","I-VP","B-NP","I-NP","O","B-NP","I-NP","I-NP","I-NP","O","B-NP","I-NP","O","O","B-VP","B-NP","I-NP"], "graphs":{ "stanford-basic":{ "edges":[{ "source":1, "destination":0, "relation":"det" },{ "source":2, "destination":1, "relation":"nsubj" },{ "source":2, "destination":5, "relation":"dobj" },{ "source":5, "destination":15, "relation":"cc" },{ "source":5, "destination":18, "relation":"conj" },{ "source":5, "destination":3, "relation":"amod" },{ "source":5, "destination":4, "relation":"amod" },{ "source":5, "destination":6, "relation":"punct" },{ "source":5, "destination":10, "relation":"conj" },{ "source":5, "destination":11, "relation":"punct" },{ "source":5, "destination":13, "relation":"conj" },{ "source":5, "destination":14, "relation":"punct" },{ "source":10, "destination":7, "relation":"amod" },{ "source":10, "destination":8, "relation":"amod" },{ "source":10, "destination":9, "relation":"nn" },{ "source":13, "destination":12, "relation":"amod" },{ "source":18, "destination":16, "relation":"amod" },{ "source":18, "destination":17, "relation":"nn" }], "roots":[2] }, "universal-enhanced":{ "edges":[{ "source":1, "destination":0, "relation":"det" },{ "source":2, "destination":1, "relation":"nsubj" },{ "source":2, "destination":18, "relation":"dobj" },{ "source":2, "destination":5, "relation":"dobj" },{ "source":2, "destination":10, "relation":"dobj" },{ "source":2, "destination":13, "relation":"dobj" },{ "source":5, "destination":18, "relation":"conj_and" },{ "source":5, "destination":3, "relation":"amod" },{ "source":5, "destination":4, "relation":"amod" },{ "source":5, "destination":6, "relation":"punct" },{ "source":5, "destination":10, "relation":"conj_and" },{ "source":5, "destination":11, "relation":"punct" },{ "source":5, "destination":13, "relation":"conj_and" },{ "source":5, "destination":14, "relation":"punct" },{ "source":10, "destination":7, "relation":"amod" },{ "source":10, "destination":8, "relation":"amod" },{ "source":10, "destination":9, "relation":"nn" },{ "source":13, "destination":12, "relation":"amod" },{ "source":18, "destination":16, "relation":"amod" },{ "source":18, "destination":17, "relation":"nn" }], "roots":[2] } } }] } """)
    val entity = new TextBoundMention(Seq("Entity"), Interval(0, doc1.sentences.head.words.size), 0, doc1, true, "test")
    val res1 = entityFinder.splitCoordinatedEntities(entity)
    res1 should have size(4)

    val doc2 = jsonStringToDocument(""" { "sentences":[{ "raw":["This","list","contains","apples",",","unripe","bananas",",","carrots",",","and","reduced","prices","."], "words":["This","list","contains","apples",",","unripe","bananas",",","carrots",",","and","reduced","prices","."], "startOffsets":[0,5,10,19,25,27,34,41,43,50,52,56,64,70], "endOffsets":[4,9,18,25,26,33,41,42,50,51,55,63,70,71], "tags":["DT","NN","VBZ","NNS",",","JJ","NNS",",","NNS",",","CC","VBD","NNS","."], "lemmas":["this","list","contain","apple",",","unripe","banana",",","carrot",",","and","reduce","price","."], "entities":["O","O","O","O","O","O","O","O","O","O","O","O","O","O"], "norms":["O","O","O","O","O","O","O","O","O","O","O","O","O","O"], "chunks":["B-NP","I-NP","B-VP","B-NP","O","B-NP","I-NP","O","B-NP","O","O","B-VP","B-NP","O"], "graphs":{ "stanford-basic":{ "edges":[{ "source":1, "destination":0, "relation":"det" },{ "source":2, "destination":1, "relation":"nsubj" },{ "source":2, "destination":3, "relation":"dobj" },{ "source":2, "destination":13, "relation":"punct" },{ "source":3, "destination":4, "relation":"punct" },{ "source":3, "destination":6, "relation":"conj" },{ "source":3, "destination":7, "relation":"punct" },{ "source":3, "destination":8, "relation":"conj" },{ "source":3, "destination":9, "relation":"punct" },{ "source":3, "destination":10, "relation":"cc" },{ "source":3, "destination":11, "relation":"conj" },{ "source":6, "destination":5, "relation":"amod" },{ "source":11, "destination":12, "relation":"dobj" }], "roots":[2] }, "universal-enhanced":{ "edges":[{ "source":1, "destination":0, "relation":"det" },{ "source":2, "destination":1, "relation":"nsubj" },{ "source":2, "destination":3, "relation":"dobj" },{ "source":2, "destination":6, "relation":"dobj" },{ "source":2, "destination":8, "relation":"dobj" },{ "source":2, "destination":11, "relation":"dobj" },{ "source":2, "destination":13, "relation":"punct" },{ "source":3, "destination":4, "relation":"punct" },{ "source":3, "destination":6, "relation":"conj_and" },{ "source":3, "destination":7, "relation":"punct" },{ "source":3, "destination":8, "relation":"conj_and" },{ "source":3, "destination":9, "relation":"punct" },{ "source":3, "destination":11, "relation":"conj_and" },{ "source":6, "destination":5, "relation":"amod" },{ "source":11, "destination":12, "relation":"dobj" }], "roots":[2] } } }] } """)
    val entity2 = new TextBoundMention(Seq("Entity"), Interval(0, doc2.sentences.head.words.size), 0, doc2, true, "test")
    val res2 = entityFinder.splitCoordinatedEntities(entity2)
    res2 should have size(4)

    // checks exclusion of 'hinder' (verb w/ arg) + coord splitting
    val doc3 = jsonStringToDocument(""" {"text":"Persisting economic crisis, governments extractive policies (high taxes), and lack of incentives and security for private-sector investment hinder development.","sentences":[{ "raw":["Persisting","economic","crisis",",","governments","extractive","policies","(","high","taxes",")",",","and","lack","of","incentives","and","security","for","private-sector","investment","hinder","development","."], "words":["Persisting","economic","crisis",",","governments","extractive","policies","(","high","taxes",")",",","and","lack","of","incentives","and","security","for","private-sector","investment","hinder","development","."],"startOffsets":[0,11,20,26,28,40,51,60,61,66,71,72,74,78,83,86,97,101,110,114,129,140,147,158],"endOffsets":[10,19,26,27,39,50,59,61,65,71,72,73,77,82,85,96,100,109,113,128,139,146,158,159],"tags":["VBG","JJ","NN",",","NNS","JJ","NNS","-LRB-","JJ","NNS","-RRB-",",","CC","NN","IN","NNS","CC","NN","IN","JJ","NN","VBP","NN","."],"lemmas":["persist","economic","crisis",",","government","extractive","policy","(","high","tax",")",",","and","lack","of","incentive","and","security","for","private-sector","investment","hinder","development","."],"entities":["O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O"],"chunks":["B-NP","I-NP","I-NP","O","B-NP","I-NP","I-NP","I-NP","I-NP","I-NP","I-NP","O","O","B-NP","B-PP","B-NP","I-NP","I-NP","B-PP","B-NP","I-NP","I-NP","I-NP","O"],"graphs":{"stanford-basic":{"edges":[{"source":0,"destination":2,"relation":"dobj"},{"source":2,"destination":1,"relation":"amod"},{"source":4,"destination":6,"relation":"dep"},{"source":4,"destination":12,"relation":"cc"},{"source":4,"destination":13,"relation":"conj"},{"source":6,"destination":5,"relation":"amod"},{"source":6,"destination":9,"relation":"appos"},{"source":9,"destination":8,"relation":"amod"},{"source":13,"destination":18,"relation":"prep"},{"source":13,"destination":14,"relation":"prep"},{"source":14,"destination":15,"relation":"pobj"},{"source":15,"destination":16,"relation":"cc"},{"source":15,"destination":17,"relation":"conj"},{"source":18,"destination":20,"relation":"pobj"},{"source":20,"destination":19,"relation":"amod"},{"source":21,"destination":0,"relation":"vmod"},{"source":21,"destination":4,"relation":"nsubj"},{"source":21,"destination":22,"relation":"dobj"}],"roots":[21]},"universal-enhanced":{"edges":[{"source":0,"destination":2,"relation":"dobj"},{"source":2,"destination":1,"relation":"amod"},{"source":4,"destination":6,"relation":"dep"},{"source":4,"destination":13,"relation":"conj_and"},{"source":6,"destination":5,"relation":"amod"},{"source":6,"destination":9,"relation":"appos"},{"source":9,"destination":8,"relation":"amod"},{"source":13,"destination":15,"relation":"prep_of"},{"source":13,"destination":17,"relation":"prep_of"},{"source":13,"destination":20,"relation":"prep_for"},{"source":15,"destination":17,"relation":"conj_and"},{"source":20,"destination":19,"relation":"amod"},{"source":21,"destination":0,"relation":"vmod"},{"source":21,"destination":4,"relation":"nsubj"},{"source":21,"destination":22,"relation":"dobj"},{"source":21,"destination":13,"relation":"nsubj"}],"roots":[21]}}}]} """)
    val res3 = entityFinder.extractAndFilter(doc3)
//    println(s"Sentence: ${doc3.sentences.head.getSentenceText()}")
//    res3.foreach(m => println(m.text))
    res3 should have size(7)


    // TODO: test extractFilteredEntities
    //    val doc1 = jsonStringToDocument(""" {"text":"The government promotes improved cultivar and climate-smart technologies but the policy to cut down the use of inorganic fertilizer and phase out the fertilizer subsidy results in deteriorating biophysical conditions, low use of inorganic fertilizer, less water, reduced farm sizes which lead to low benefit from the improved cultivar.","sentences":[{"words":["The","government","promotes","improved","cultivar","and","climate-smart","technologies","but","the","policy","to","cut","down","the","use","of","inorganic","fertilizer","and","phase","out","the","fertilizer","subsidy","results","in","deteriorating","biophysical","conditions",",","low","use","of","inorganic","fertilizer",",","less","water",",","reduced","farm","sizes","which","lead","to","low","benefit","from","the","improved","cultivar","."],"startOffsets":[0,4,15,24,33,42,46,60,73,77,81,88,91,95,100,104,108,111,121,132,136,142,146,150,161,169,177,180,194,206,216,218,222,226,229,239,249,251,256,261,263,271,276,282,288,293,296,300,308,313,317,326,334],"endOffsets":[3,14,23,32,41,45,59,72,76,80,87,90,94,99,103,107,110,120,131,135,141,145,149,160,168,176,179,193,205,216,217,221,225,228,238,249,250,255,261,262,270,275,281,287,292,295,299,307,312,316,325,334,335],"tags":["DT","NN","VBZ","VBN","NN","CC","JJ","NNS","CC","DT","NN","TO","VB","RP","DT","NN","IN","JJ","NN","CC","NN","IN","DT","NN","NN","VBZ","IN","VBG","JJ","NNS",",","JJ","NN","IN","JJ","NN",",","JJR","NN",",","VBD","NN","NNS","WDT","VBP","TO","JJ","NN","IN","DT","JJ","NN","."],"lemmas":["the","government","promote","improve","cultivar","and","climate-smart","technology","but","the","policy","to","cut","down","the","use","of","inorganic","fertilizer","and","phase","out","the","fertilizer","subsidy","result","in","deteriorate","biophysical","condition",",","low","use","of","inorganic","fertilizer",",","less","water",",","reduce","farm","size","which","lead","to","low","benefit","from","the","improved","cultivar","."],"entities":["O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","B-Simple_chemical","O","O","O","O","O","O","O","O","O","O","O","O","O","O"],"chunks":["B-NP","I-NP","B-VP","B-NP","I-NP","I-NP","I-NP","I-NP","O","B-NP","I-NP","B-VP","I-VP","B-PRT","B-NP","I-NP","B-PP","B-NP","I-NP","O","B-NP","B-PP","B-NP","I-NP","I-NP","B-VP","B-PP","B-VP","B-NP","I-NP","O","B-NP","I-NP","B-PP","B-NP","I-NP","O","B-NP","I-NP","O","B-VP","B-NP","I-NP","B-NP","B-VP","B-PP","B-NP","I-NP","B-PP","B-NP","I-NP","I-NP","O"],"graphs":{"stanford-basic":{"edges":[{"source":1,"destination":0,"relation":"det"},{"source":2,"destination":1,"relation":"nsubj"},{"source":2,"destination":25,"relation":"ccomp"},{"source":4,"destination":3,"relation":"amod"},{"source":4,"destination":5,"relation":"cc"},{"source":4,"destination":7,"relation":"conj"},{"source":4,"destination":8,"relation":"prep"},{"source":7,"destination":6,"relation":"amod"},{"source":8,"destination":10,"relation":"pobj"},{"source":10,"destination":9,"relation":"det"},{"source":10,"destination":12,"relation":"vmod"},{"source":12,"destination":15,"relation":"dobj"},{"source":12,"destination":21,"relation":"prep"},{"source":12,"destination":11,"relation":"aux"},{"source":12,"destination":13,"relation":"prt"},{"source":15,"destination":16,"relation":"prep"},{"source":15,"destination":14,"relation":"det"},{"source":16,"destination":18,"relation":"pobj"},{"source":18,"destination":17,"relation":"amod"},{"source":18,"destination":19,"relation":"cc"},{"source":18,"destination":20,"relation":"conj"},{"source":21,"destination":24,"relation":"pobj"},{"source":24,"destination":22,"relation":"det"},{"source":24,"destination":23,"relation":"nn"},{"source":25,"destination":4,"relation":"nsubj"},{"source":25,"destination":26,"relation":"prep"},{"source":26,"destination":29,"relation":"pobj"},{"source":29,"destination":27,"relation":"amod"},{"source":29,"destination":28,"relation":"amod"},{"source":32,"destination":31,"relation":"amod"},{"source":32,"destination":33,"relation":"prep"},{"source":33,"destination":35,"relation":"pobj"},{"source":35,"destination":34,"relation":"amod"},{"source":35,"destination":38,"relation":"appos"},{"source":38,"destination":37,"relation":"amod"},{"source":40,"destination":32,"relation":"nsubj"},{"source":40,"destination":2,"relation":"ccomp"},{"source":40,"destination":42,"relation":"dobj"},{"source":42,"destination":41,"relation":"nn"},{"source":42,"destination":44,"relation":"rcmod"},{"source":44,"destination":48,"relation":"prep"},{"source":44,"destination":43,"relation":"nsubj"},{"source":44,"destination":45,"relation":"prep"},{"source":45,"destination":47,"relation":"pobj"},{"source":47,"destination":46,"relation":"amod"},{"source":48,"destination":51,"relation":"pobj"},{"source":51,"destination":49,"relation":"det"},{"source":51,"destination":50,"relation":"amod"}],"roots":[40]},"universal-enhanced":{"edges":[{"source":1,"destination":0,"relation":"det"},{"source":2,"destination":1,"relation":"nsubj"},{"source":2,"destination":25,"relation":"ccomp"},{"source":4,"destination":3,"relation":"amod"},{"source":4,"destination":7,"relation":"conj_and"},{"source":4,"destination":8,"relation":"prep"},{"source":7,"destination":6,"relation":"amod"},{"source":8,"destination":10,"relation":"pobj"},{"source":10,"destination":9,"relation":"det"},{"source":10,"destination":12,"relation":"vmod"},{"source":12,"destination":15,"relation":"dobj"},{"source":12,"destination":24,"relation":"prep_out"},{"source":12,"destination":11,"relation":"aux"},{"source":12,"destination":13,"relation":"prt"},{"source":15,"destination":18,"relation":"prep_of"},{"source":15,"destination":20,"relation":"prep_of"},{"source":15,"destination":14,"relation":"det"},{"source":18,"destination":17,"relation":"amod"},{"source":18,"destination":20,"relation":"conj_and"},{"source":24,"destination":22,"relation":"det"},{"source":24,"destination":23,"relation":"nn"},{"source":25,"destination":4,"relation":"nsubj"},{"source":25,"destination":7,"relation":"nsubj"},{"source":25,"destination":29,"relation":"prep_in"},{"source":29,"destination":27,"relation":"amod"},{"source":29,"destination":28,"relation":"amod"},{"source":32,"destination":31,"relation":"amod"},{"source":32,"destination":35,"relation":"prep_of"},{"source":35,"destination":34,"relation":"amod"},{"source":35,"destination":38,"relation":"appos"},{"source":38,"destination":37,"relation":"amod"},{"source":40,"destination":32,"relation":"nsubj"},{"source":40,"destination":2,"relation":"ccomp"},{"source":40,"destination":42,"relation":"dobj"},{"source":42,"destination":41,"relation":"nn"},{"source":42,"destination":44,"relation":"rcmod"},{"source":44,"destination":47,"relation":"prep_to"},{"source":44,"destination":51,"relation":"prep_from"},{"source":44,"destination":43,"relation":"nsubj"},{"source":47,"destination":46,"relation":"amod"},{"source":51,"destination":49,"relation":"det"},{"source":51,"destination":50,"relation":"amod"}],"roots":[40]}}}]} """)
    //    val entities = EntityFinder.extractAndFilter(doc1)
    //    entities.forall(e => ! e.tags.get.contains("CC")) should be (true)

    val doc4 = jsonStringToDocument(""" {"sentences":[{ "raw":["The","support","for","agricultural","research",",","education",",","and","extension","programs","will","also","be","increased","for","developing","and","disseminating","climate","change","adaptation","agricultural","technologies","to","the","farmers","."], "words":["The","support","for","agricultural","research",",","education",",","and","extension","programs","will","also","be","increased","for","developing","and","disseminating","climate","change","adaptation","agricultural","technologies","to","the","farmers","."],"startOffsets":[0,4,12,16,29,38,40,50,52,56,66,75,80,85,88,98,102,113,117,131,139,146,157,170,183,186,190,197],"endOffsets":[3,11,15,28,37,39,49,51,55,65,74,79,84,87,97,101,112,116,130,138,145,156,169,182,185,189,197,198],"tags":["DT","NN","IN","JJ","NN",",","NN",",","CC","NN","NNS","MD","RB","VB","VBN","IN","VBG","CC","VBG","NN","NN","NN","JJ","NNS","TO","DT","NNS","."],"lemmas":["the","support","for","agricultural","research",",","education",",","and","extension","program","will","also","be","increase","for","develop","and","disseminate","climate","change","adaptation","agricultural","technology","to","the","farmer","."],"entities":["O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O"],"norms":["O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O"],"chunks":["B-NP","I-NP","B-PP","B-NP","I-NP","I-NP","I-NP","I-NP","I-NP","I-NP","I-NP","B-VP","I-VP","I-VP","I-VP","B-PP","B-NP","I-NP","I-NP","I-NP","I-NP","I-NP","I-NP","I-NP","B-PP","B-NP","I-NP","O"],"graphs":{"stanford-basic":{"edges":[{"source":1,"destination":0,"relation":"det"},{"source":1,"destination":2,"relation":"prep"},{"source":2,"destination":4,"relation":"pobj"},{"source":4,"destination":3,"relation":"amod"},{"source":4,"destination":5,"relation":"punct"},{"source":4,"destination":6,"relation":"conj"},{"source":4,"destination":7,"relation":"punct"},{"source":4,"destination":8,"relation":"cc"},{"source":4,"destination":10,"relation":"conj"},{"source":10,"destination":9,"relation":"nn"},{"source":14,"destination":15,"relation":"prep"},{"source":14,"destination":1,"relation":"nsubjpass"},{"source":14,"destination":11,"relation":"aux"},{"source":14,"destination":27,"relation":"punct"},{"source":14,"destination":12,"relation":"advmod"},{"source":14,"destination":13,"relation":"auxpass"},{"source":15,"destination":16,"relation":"pcomp"},{"source":16,"destination":17,"relation":"cc"},{"source":16,"destination":18,"relation":"conj"},{"source":16,"destination":23,"relation":"dobj"},{"source":16,"destination":24,"relation":"prep"},{"source":23,"destination":19,"relation":"nn"},{"source":23,"destination":20,"relation":"nn"},{"source":23,"destination":21,"relation":"nn"},{"source":23,"destination":22,"relation":"amod"},{"source":24,"destination":26,"relation":"pobj"},{"source":26,"destination":25,"relation":"det"}],"roots":[14]},"universal-enhanced":{"edges":[{"source":1,"destination":0,"relation":"det"},{"source":1,"destination":4,"relation":"prep_for"},{"source":1,"destination":6,"relation":"prep_for"},{"source":1,"destination":10,"relation":"prep_for"},{"source":4,"destination":3,"relation":"amod"},{"source":4,"destination":5,"relation":"punct"},{"source":4,"destination":6,"relation":"conj_and"},{"source":4,"destination":7,"relation":"punct"},{"source":4,"destination":10,"relation":"conj_and"},{"source":10,"destination":9,"relation":"nn"},{"source":14,"destination":16,"relation":"prepc_for"},{"source":14,"destination":1,"relation":"nsubjpass"},{"source":14,"destination":18,"relation":"prepc_for"},{"source":14,"destination":11,"relation":"aux"},{"source":14,"destination":27,"relation":"punct"},{"source":14,"destination":12,"relation":"advmod"},{"source":14,"destination":13,"relation":"auxpass"},{"source":16,"destination":18,"relation":"conj_and"},{"source":16,"destination":23,"relation":"dobj"},{"source":16,"destination":26,"relation":"prep_to"},{"source":23,"destination":19,"relation":"nn"},{"source":23,"destination":20,"relation":"nn"},{"source":23,"destination":21,"relation":"nn"},{"source":23,"destination":22,"relation":"amod"},{"source":26,"destination":25,"relation":"det"}],"roots":[14]}}}]} """)
    val res4 = entityFinder.extractAndFilter(doc4)
    res4 should have size(7)

  }

  "Orphaned adjectives" should "not be considered entities" in {

    val doc1 = jsonStringToDocument(""" {"text":"The government promotes improved cultivar and climate-smart technologies but the policy to cut down the use of inorganic fertilizer and phase out the fertilizer subsidy results in deteriorating biophysical conditions, low use of inorganic fertilizer, less water, reduced farm sizes which lead to low benefit from the improved cultivar.","sentences":[{ "raw":["The","government","promotes","improved","cultivar","and","climate-smart","technologies","but","the","policy","to","cut","down","the","use","of","inorganic","fertilizer","and","phase","out","the","fertilizer","subsidy","results","in","deteriorating","biophysical","conditions",",","low","use","of","inorganic","fertilizer",",","less","water",",","reduced","farm","sizes","which","lead","to","low","benefit","from","the","improved","cultivar","."], "words":["The","government","promotes","improved","cultivar","and","climate-smart","technologies","but","the","policy","to","cut","down","the","use","of","inorganic","fertilizer","and","phase","out","the","fertilizer","subsidy","results","in","deteriorating","biophysical","conditions",",","low","use","of","inorganic","fertilizer",",","less","water",",","reduced","farm","sizes","which","lead","to","low","benefit","from","the","improved","cultivar","."],"startOffsets":[0,4,15,24,33,42,46,60,73,77,81,88,91,95,100,104,108,111,121,132,136,142,146,150,161,169,177,180,194,206,216,218,222,226,229,239,249,251,256,261,263,271,276,282,288,293,296,300,308,313,317,326,334],"endOffsets":[3,14,23,32,41,45,59,72,76,80,87,90,94,99,103,107,110,120,131,135,141,145,149,160,168,176,179,193,205,216,217,221,225,228,238,249,250,255,261,262,270,275,281,287,292,295,299,307,312,316,325,334,335],"tags":["DT","NN","VBZ","VBN","NN","CC","JJ","NNS","CC","DT","NN","TO","VB","RP","DT","NN","IN","JJ","NN","CC","NN","IN","DT","NN","NN","VBZ","IN","VBG","JJ","NNS",",","JJ","NN","IN","JJ","NN",",","JJR","NN",",","VBD","NN","NNS","WDT","VBP","TO","JJ","NN","IN","DT","JJ","NN","."],"lemmas":["the","government","promote","improve","cultivar","and","climate-smart","technology","but","the","policy","to","cut","down","the","use","of","inorganic","fertilizer","and","phase","out","the","fertilizer","subsidy","result","in","deteriorate","biophysical","condition",",","low","use","of","inorganic","fertilizer",",","less","water",",","reduce","farm","size","which","lead","to","low","benefit","from","the","improved","cultivar","."],"entities":["O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","B-Simple_chemical","O","O","O","O","O","O","O","O","O","O","O","O","O","O"],"chunks":["B-NP","I-NP","B-VP","B-NP","I-NP","I-NP","I-NP","I-NP","O","B-NP","I-NP","B-VP","I-VP","B-PRT","B-NP","I-NP","B-PP","B-NP","I-NP","O","B-NP","B-PP","B-NP","I-NP","I-NP","B-VP","B-PP","B-VP","B-NP","I-NP","O","B-NP","I-NP","B-PP","B-NP","I-NP","O","B-NP","I-NP","O","B-VP","B-NP","I-NP","B-NP","B-VP","B-PP","B-NP","I-NP","B-PP","B-NP","I-NP","I-NP","O"],"graphs":{"stanford-basic":{"edges":[{"source":1,"destination":0,"relation":"det"},{"source":2,"destination":1,"relation":"nsubj"},{"source":2,"destination":25,"relation":"ccomp"},{"source":4,"destination":3,"relation":"amod"},{"source":4,"destination":5,"relation":"cc"},{"source":4,"destination":7,"relation":"conj"},{"source":4,"destination":8,"relation":"prep"},{"source":7,"destination":6,"relation":"amod"},{"source":8,"destination":10,"relation":"pobj"},{"source":10,"destination":9,"relation":"det"},{"source":10,"destination":12,"relation":"vmod"},{"source":12,"destination":15,"relation":"dobj"},{"source":12,"destination":21,"relation":"prep"},{"source":12,"destination":11,"relation":"aux"},{"source":12,"destination":13,"relation":"prt"},{"source":15,"destination":16,"relation":"prep"},{"source":15,"destination":14,"relation":"det"},{"source":16,"destination":18,"relation":"pobj"},{"source":18,"destination":17,"relation":"amod"},{"source":18,"destination":19,"relation":"cc"},{"source":18,"destination":20,"relation":"conj"},{"source":21,"destination":24,"relation":"pobj"},{"source":24,"destination":22,"relation":"det"},{"source":24,"destination":23,"relation":"nn"},{"source":25,"destination":4,"relation":"nsubj"},{"source":25,"destination":26,"relation":"prep"},{"source":26,"destination":29,"relation":"pobj"},{"source":29,"destination":27,"relation":"amod"},{"source":29,"destination":28,"relation":"amod"},{"source":32,"destination":31,"relation":"amod"},{"source":32,"destination":33,"relation":"prep"},{"source":33,"destination":35,"relation":"pobj"},{"source":35,"destination":34,"relation":"amod"},{"source":35,"destination":38,"relation":"appos"},{"source":38,"destination":37,"relation":"amod"},{"source":40,"destination":32,"relation":"nsubj"},{"source":40,"destination":2,"relation":"ccomp"},{"source":40,"destination":42,"relation":"dobj"},{"source":42,"destination":41,"relation":"nn"},{"source":42,"destination":44,"relation":"rcmod"},{"source":44,"destination":48,"relation":"prep"},{"source":44,"destination":43,"relation":"nsubj"},{"source":44,"destination":45,"relation":"prep"},{"source":45,"destination":47,"relation":"pobj"},{"source":47,"destination":46,"relation":"amod"},{"source":48,"destination":51,"relation":"pobj"},{"source":51,"destination":49,"relation":"det"},{"source":51,"destination":50,"relation":"amod"}],"roots":[40]},"universal-enhanced":{"edges":[{"source":1,"destination":0,"relation":"det"},{"source":2,"destination":1,"relation":"nsubj"},{"source":2,"destination":25,"relation":"ccomp"},{"source":4,"destination":3,"relation":"amod"},{"source":4,"destination":7,"relation":"conj_and"},{"source":4,"destination":8,"relation":"prep"},{"source":7,"destination":6,"relation":"amod"},{"source":8,"destination":10,"relation":"pobj"},{"source":10,"destination":9,"relation":"det"},{"source":10,"destination":12,"relation":"vmod"},{"source":12,"destination":15,"relation":"dobj"},{"source":12,"destination":24,"relation":"prep_out"},{"source":12,"destination":11,"relation":"aux"},{"source":12,"destination":13,"relation":"prt"},{"source":15,"destination":18,"relation":"prep_of"},{"source":15,"destination":20,"relation":"prep_of"},{"source":15,"destination":14,"relation":"det"},{"source":18,"destination":17,"relation":"amod"},{"source":18,"destination":20,"relation":"conj_and"},{"source":24,"destination":22,"relation":"det"},{"source":24,"destination":23,"relation":"nn"},{"source":25,"destination":4,"relation":"nsubj"},{"source":25,"destination":7,"relation":"nsubj"},{"source":25,"destination":29,"relation":"prep_in"},{"source":29,"destination":27,"relation":"amod"},{"source":29,"destination":28,"relation":"amod"},{"source":32,"destination":31,"relation":"amod"},{"source":32,"destination":35,"relation":"prep_of"},{"source":35,"destination":34,"relation":"amod"},{"source":35,"destination":38,"relation":"appos"},{"source":38,"destination":37,"relation":"amod"},{"source":40,"destination":32,"relation":"nsubj"},{"source":40,"destination":2,"relation":"ccomp"},{"source":40,"destination":42,"relation":"dobj"},{"source":42,"destination":41,"relation":"nn"},{"source":42,"destination":44,"relation":"rcmod"},{"source":44,"destination":47,"relation":"prep_to"},{"source":44,"destination":51,"relation":"prep_from"},{"source":44,"destination":43,"relation":"nsubj"},{"source":47,"destination":46,"relation":"amod"},{"source":51,"destination":49,"relation":"det"},{"source":51,"destination":50,"relation":"amod"}],"roots":[40]}}}]} """)
    val entities = entityFinder.extractAndFilter(doc1)
    entities.forall(_.text != "significant") should be (true)

  }

}
