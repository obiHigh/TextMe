package com.example.textme

import android.app.Application
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.realtime.Realtime

class MyApp : Application() {
    companion object {
        lateinit var supabase: SupabaseClient
    }

    override fun onCreate() {
        super.onCreate()

         supabase = createSupabaseClient(
            supabaseUrl = "https://ahgbniqcqpicmerqdpzv.supabase.co",
            supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImFoZ2JuaXFjcXBpY21lcnFkcHp2Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3NTM0NTk2ODcsImV4cCI6MjA2OTAzNTY4N30.DiNXMCpqmrybK7K05OtIIFKL4KXOgvW_vmivzj6SAxU"
        ) {

             install(Auth)
             install(Postgrest)
             install(Realtime)
        }
    }
}