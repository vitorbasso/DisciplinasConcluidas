module Queue2 (Queue,
					enqueue, dequeue,
					front, empty, isEmpty) where
					
						data Queue a = Q ([a], [a])
						
						norm :: ([a], [a])->([a], [a])
						norm([], tr) = (reverse tr, [])
						norm (fr, tr) = (fr,tr)
						
						enqueue :: a->Queue a->Queue a
						enqueue x (Q, (fr, tr)) = Q (norm (fr, x:tr))
						
						dequeue :: Queue a->Queue a
						dequeue (Q (x:fr , tr)) = (Q (fr, tr))
						dequeue _ = error ("Queue.dequeue: empty queue")
						
						front :: Queue a->a
						front (Q (x:fr, tr)) = x
						front _ = error "Queue.front: empty queue"
						
						empty :: Queue a
						empty = Q ([] , [])
						
						isEmpty :: Queue a->Bool
						isEmpty (Q, ([], _)) = True
						isEmpty (Q, (_,_)) = False