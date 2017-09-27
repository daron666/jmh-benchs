package scrorex.benchmarks

import java.util.concurrent.TimeUnit

import org.openjdk.jmh.annotations._
import scorex.crypto.authds.avltree.batch.Operation
import scrorex.benchmarks.Helper.{Prover, generateInserts, getProver}

object AVLBatchPerformance {

  @State(Scope.Thread)
  class Basic(proverCnt: Int, opsCnt: Int) {

    val preparedOperations = proverCnt
    val operationsToApply = opsCnt

    var prover: Prover = _
    var operations: Seq[Operation] = _

    @Setup(Level.Iteration)
    def up: Unit = {
      prover = getProver(preparedOperations)
      val inserts = generateInserts(preparedOperations until (preparedOperations + operationsToApply))
      operations = inserts
    }
  }

  @State(Scope.Thread)
  class StateWith1000000 extends Basic(1000000, 100000)

  @State(Scope.Thread)
  class StateWith2000000 extends Basic(2000000, 100000)

  @State(Scope.Thread)
  class StateWith3000000 extends Basic(3000000, 100000)

  @State(Scope.Thread)
  class StateWith4000000 extends Basic(4000000, 100000)

  @State(Scope.Thread)
  class StateWith5000000 extends Basic(5000000, 100000)

}

@State(Scope.Thread)
@BenchmarkMode(Array(Mode.AverageTime))
@OutputTimeUnit(TimeUnit.SECONDS)
class AVLBatchPerformance {
  import AVLBatchPerformance._

  @Benchmark
  def apply100000inBatchesOf2000opsToProver1000000(s: StateWith1000000): Unit = {
    import s._
    operations.grouped(2000).foreach { batch =>
      batch.foreach(prover.performOneOperation)
      prover.generateProof()
    }
  }

  @Benchmark
  def apply100000inBatchesOf2000opsToProver2000000(s: StateWith2000000): Unit = {
    import s._
    operations.grouped(2000).foreach { batch =>
      batch.foreach(prover.performOneOperation)
      prover.generateProof()
    }
  }

  @Benchmark
  def apply100000inBatchesOf2000opsToProver3000000(s: StateWith3000000): Unit = {
    import s._
    operations.grouped(2000).foreach { batch =>
      batch.foreach(prover.performOneOperation)
      prover.generateProof()
    }
  }

  @Benchmark
  def apply100000inBatchesOf2000opsToProver4000000(s: StateWith4000000): Unit = {
    import s._
    operations.grouped(2000).foreach { batch =>
      batch.foreach(prover.performOneOperation)
      prover.generateProof()
    }
  }

  @Benchmark
  def apply100000inBatchesOf2000opsToProver5000000(s: StateWith5000000): Unit = {
    import s._
    operations.grouped(2000).foreach { batch =>
      batch.foreach(prover.performOneOperation)
      prover.generateProof()
    }
  }

}
