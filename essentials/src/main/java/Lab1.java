/*
 * Copyright (c) 2008-2019, Hazelcast, Inc. All Rights Reserved.
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

import com.hazelcast.jet.Jet;
import com.hazelcast.jet.JetInstance;
import com.hazelcast.jet.pipeline.Pipeline;
import com.hazelcast.jet.pipeline.Sinks;
import com.hazelcast.jet.pipeline.StreamSource;
import com.hazelcast.jet.pipeline.test.TestSources;

public class Lab1 {

    public static void main (String[] args) {
        Pipeline p = buildPipeline();

        JetInstance jet = Jet.newJetInstance();

        try {
            jet.newJob(p).join();
        } finally {
            jet.shutdown();
        }
    }

    private static Pipeline buildPipeline() {
        Pipeline p = Pipeline.create();

        // STEP 1: Print a stream to the console

        StreamSource<Long> source = TestSources.itemStream(1, (ts, seq) -> seq);

        p.drawFrom(source)
         .withoutTimestamps()
         .drainTo(Sinks.logger());

        // Run the code to see the results in the console
        // Stop it before continuing to step 2



        // STEP 2: Filter out odd numbers from the stream

        // Add filter() to  your pipeline
        // - Use lambda to define the predicate



        // STEP 3: Process data from a file instead of generated data

        // Create a directory somewhere in your computer and create an empty input.txt file in it

        // Replace itemStream with fileWatcher source from com.hazelcast.jet.pipeline.Sources
        // - (fileWatcher stream lines added to files in a directory.)
        // - Adjust source type - the generator was producing Longs, fileWatcher produces Strings

        // Add a mapping step before the filter to convert the stream from Strings to Longs

        // Run this pipeline to test it!
        // - Add text lines to the file.
        // - Use echo -- some text editors create a new file for every save. That results in replaying the file.
        //
        // echo "0" >> input.txt
        // echo "1" >> input.txt

        // Stop the job


        return p;
    }
}
