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

import com.google.cloud.language.v1.AnalyzeSentimentResponse;
import com.google.cloud.language.v1.Document;
import com.google.cloud.language.v1.LanguageServiceClient;
import com.google.cloud.language.v1.Sentiment;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@SpringBootApplication
@RestController
@RequestMapping
public class QuizApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuizApplication.class, args);
	}

	@GetMapping("language")
	public String getLanguage() {

		// This snippet has been automatically generated and should be regarded as a code template only.
		// It will require modifications to work:
		// - It may require correct/in-range values for request initialization.
		// - It may require specifying regional endpoints when creating the service client as shown in
		// https://cloud.google.com/java/docs/setup#configure_endpoints_for_the_client_library
		try (LanguageServiceClient languageServiceClient = LanguageServiceClient.create()) {
			Document document = Document.newBuilder().setLanguage("english").setContent("After a yearlong investigation, Hur concluded that Biden mishandled classified material and improperly disclosed classified information as a private citizen. However, Hur said that there wasn’t enough evidence to prosecute Biden, and that he wouldn’t have pursued charges even if the Justice Department allowed him to indict a sitting president.\n" +
					"But it was Hur’s assessment of Biden’s cognitive abilities that sent shockwaves throughout Washington. He described Biden as a “well-meaning, elderly man with a poor memory” in explaining the uphill battle prosecutors might face when trying to convince a potential jury that Biden was a criminal.\n" +
					"CNN reported Tuesday morning that Biden told jokes and unfurled lengthy detailed stories from his decadeslong political career as he parried questions from Hur and his investigators over two days last October, a transcript of the interview reviewed by news outlets shows. The president also displayed episodes of foggy memory, including one highlighted in Hur’s final report, in which the president appeared not to remember the year that his son Beau died.").build();
			AnalyzeSentimentResponse response = languageServiceClient.analyzeSentiment(document);
			Sentiment sentiment = response.getDocumentSentiment();
			System.out.print("Sentiment = " + sentiment);
			return Float.toString(sentiment.getScore());
		} catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
