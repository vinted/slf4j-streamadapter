[![Build Status](https://travis-ci.org/vinted/slf4j-streamadapter.svg?branch=master)](https://travis-ci.org/vinted/slf4j-streamadapter)
[![Release](https://jitpack.io/v/vinted/slf4j-streamadapter.svg)](https://jitpack.io/#vinted/slf4j-streamadapter)

This adapter works as workaround if you are not able otherwise get output of the SLF4J logger.
This adapter is only adapter, not a backend of the logger.

### Usage

```kotlin
val outputStream = ByteArrayOutputStream()
val logger = SLF4JOutputStreamAdapter(outputStream)

anotherLib.customLogger(logger)

// ...

println(outputStream.toString())
```

### Download

```
repositories {
    jcenter()
    maven { url "https://jitpack.io" }
}

dependencies {
    implementation 'com.vinted:slf4j-streamadapter:1.0.0'
}
```

### License

```
MIT License

Copyright (c) 2019 Vinted

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
