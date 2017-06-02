package com.yansoft.jobs

trait Job {

  def start(): Unit

  def stop(): Unit

  def name: String

}