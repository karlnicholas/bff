/*
 * Copyright 2018 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.training.appdev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping
public class QuizApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuizApplication.class, args);
	}

	@GetMapping("accounts")
	public Accounts getAccounts(@RequestParam("user") String uid) {
			return Accounts.builder().accounts(List.of("checking", "saving", uid)).build();
//		try (LanguageServiceClient language = LanguageServiceClient.create()) {
//			Document doc =
//					Document.newBuilder().setContent("After a yearlong investigation, Hur concluded that Biden mishandled classified material and improperly disclosed classified information as a private citizen. However, Hur said that there wasn’t enough evidence to prosecute Biden, and that he wouldn’t have pursued charges even if the Justice Department allowed him to indict a sitting president. But it was Hur’s assessment of Biden’s cognitive abilities that sent shockwaves throughout Washington. He described Biden as a “well-meaning, elderly man with a poor memory” in explaining the uphill battle prosecutors might face when trying to convince a potential jury that Biden was a criminal. CNN reported Tuesday morning that Biden told jokes and unfurled lengthy detailed stories from his decadeslong political career as he parried questions from Hur and his investigators over two days last October, a transcript of the interview reviewed by news outlets shows. The president also displayed episodes of foggy memory, including one highlighted in Hur’s final report, in which the president appeared not to remember the year that his son Beau died.")
//							.setType(Document.Type.PLAIN_TEXT).build();
//			AnalyzeSentimentResponse response = language.analyzeSentiment(doc);
//			Sentiment sentiment = response.getDocumentSentiment();
//			if (sentiment == null) {
//				System.out.println("No sentiment found");
//			} else {
//				System.out.printf("Sentiment magnitude : %.3f\n", sentiment.getMagnitude());
//				System.out.printf("Sentiment score : %.3f\n", sentiment.getScore());
//			}
//			System.out.print("Sentiment = " + sentiment);
//			return Float.toString(sentiment.getScore());
//		} catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }
}
