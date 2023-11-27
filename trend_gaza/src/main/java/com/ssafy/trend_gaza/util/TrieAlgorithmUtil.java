package com.ssafy.trend_gaza.util;


import com.ssafy.trend_gaza.attraction.dto.AttractionAutoSearchResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class TrieAlgorithmUtil {

    public static class Node {
        Map<Character, Node> chiledNode = new HashMap<Character, Node>(); // 자식노드
        boolean endOfword; // 단어의 끝인지 아닌지 체크
        int contentId; // 관광지 아이디
    }

    List<AttractionAutoSearchResponse> autoCompleteStr;

    /*
     *  Trie자료구조를 생성할 떄 rootNode는 기본적으로 생성
     */
    Node rootNode = new Node();

    /*
     * Trie에 문자열 저장
     */
    public void insert(int contentId, String str) {
        Node node = this.rootNode; // Trie자료구조는 항상 rootNode부터 시작

        // 문자열의 각 단어마다 가져와서 자식노드 중에 있는지 체크
        // 없으면 자식노드 새로 생성
        for (int i = 0; i < str.length(); i++) {
            node = node.chiledNode.computeIfAbsent(str.charAt(i), key -> new Node());
        }

        node.endOfword = true; // 저장 할 문자열의 마지막 단어에 매핑되는 노드에 단어의 끝임을 명시
        node.contentId = contentId;
    }

    /*
     * Trie에서 str로 시작하는 문자열들을 검색
     * str = 검색할 키워드
     */
    public Node search(String str) {
        // Trie자료구조는 항상 rootNode부터 시작
        Node node = this.rootNode;

        // 문자열의 각 단어마다 노드가 존재하는지 체크
        for (int i = 0; i < str.length(); i++) {
            node =
                    node.chiledNode.getOrDefault(
                            str.charAt(i), null); // 문자열의 각 단어에 매핑된 노드가 존재하면 가져오고 아니면 null
            if (node == null) { // node가 null이면 현재 Trie에 해당 문자열은 없음
                return null;
            }
        }
        return node;
    }

    /*
     * 자동 완성 기능
     * 사용자가 입력한 문자열로 시작하는 문자열들을 찾는다.
     * str = 사용자가 검색한 문자열
     * node = 사용자가 검색한 문자열로 시작하는 문자열을 담은 객체
     */
    public List<AttractionAutoSearchResponse> autoComplete(String str, Node node) {
        autoCompleteStr = new ArrayList<AttractionAutoSearchResponse>();

        dfs(node, str);

        return autoCompleteStr;
    }

    /*
     * node = 사용자가 검색한 문자열로 시작하는 문자열을 담은 객체
     * str = 연관 검색어 키워드
     * '국립중앙박물관'으로 시작하는 키워드를 찾을 때 '국립중앙박물관', '국립중앙박물관어린이박물관'이 나오려면,
     * chiledNode가 null일 때 return해야 한다.
     */
    public void dfs(Node node, String str) {
        if (node.endOfword == true) {
            autoCompleteStr.add(
                    AttractionAutoSearchResponse.builder()
                            .title(str)
                            .contentId(node.contentId)
                            .build());
        }

        if (node.chiledNode == null) {
            // autoCompleteStr.add(str);
            autoCompleteStr.add(
                    AttractionAutoSearchResponse.builder()
                            .title(str)
                            .contentId(node.contentId)
                            .build());
            return;
        }

        for (Character key : node.chiledNode.keySet()) {
            dfs(node.chiledNode.get(key), str + key);
        }
    }
}
