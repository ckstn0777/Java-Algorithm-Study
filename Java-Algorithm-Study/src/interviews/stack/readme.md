### 스택 라이브리리 이해하기
- 자바에서 스택을 표현하는 좋은 방법은 Deque 인터페이스를 사용하는 것이다. 
- Array Deque 클래스는 이 인터페이스를 이용한 가변 배열이며, 스택과 큐 기능을 제공한다.
- 핵심 메서드
  - push(e) : 원소를 스택에 집어넣는 연산. 구현에 따라 용량의 한계를 넘어가면 예외를 던지거나, null을 삽입했을때 예외를 던질 수 있다.
  - peak() : 원소를 삭제하지 않고 스택의 위에 있는 원소를 반환하는 연산. 다만, 그렇다고 해도 isEmpty()를 사용해 스택이 비어있는지 확인하는게 좋다.
  - pop() : 스택에서 원소를 삭제하고 그 원소를 반환하는 연산이다. 만약 덱이 비어 있다면 예외를 발생시킨다.
