## 해시 테이블
### 해시 테이블 라이브러리 
- HashSet과 HashMap은 자바에서 흔하게 사용하는 해시 테이블 기반 자료구조이다. 
- HashMap은 키와 값의 쌍을 저장하는데 반해 HashSet은 키만 저장한다는 점이 다르다. 
- 이 둘 모두 중복된 키를 저장할 수 없다. 
- 또한, 이 둘 모두 순서를 보장하지 않는다. 순서를 보장받고 싶다면 LinkedHashSet, LinkedHashMap을 사용하면 된다. 

### HashMap
- 값을 수정할 때 put 과 replace 치이 점
  - put : 키가 없는 경우 put 메서드는 해당 키와 값을 저장해준다. 
  - replace : 키가 없는 경우 null을 반환한다. 따라서 replace가 값을 업데이트할 때 좀 더 안전하다. 