package br.edu.ulbra.election.candidate.client;

import br.edu.ulbra.election.candidate.output.v1.ElectionOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class ElectionClientService {

    private final ElectionClient electionClient;

    @Autowired
    public ElectionClientService(ElectionClient electionClient){
        this.electionClient = electionClient;
    }

    public ElectionOutput getById(Long id){
        return this.electionClient.getById(id);
    }

    public Long countVotesByElectionId(Long electionId){
        return this.electionClient.countVotesByElectionId(electionId);
    }

    @FeignClient(value="election-service", url="http://localhost:8084")
    private interface ElectionClient {

        @GetMapping("/v1/election/{electionId}")
        ElectionOutput getById(@PathVariable(name = "electionId") Long electionId);

        @GetMapping("/v1/vote/getvote/{electionId}")
        Long countVotesByElectionId(@PathVariable(name = "electionId") Long electionId);

    }
}
